package net.stupidiot.iothingers.weave.integrator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.clouddevices.CloudDevices;
import com.google.api.services.clouddevices.model.Device;
import com.google.api.services.clouddevices.model.DevicesListResponse;
import com.google.gson.Gson;

import net.stupidiot.iothingers.dao.SectionDao;

@Service
public class SectionCountUpdater
{
    private static final Logger LOG = LoggerFactory.getLogger(SectionCountUpdater.class);

    // See
    // https://developers.google.com/weave/v1/dev-guides/getting-started/authorizing#setup
    // on how to set up your project and obtain client ID, client secret and API
    // key.
    private static final String CLIENT_ID = "373568692871-vr6ltg605j9lok7ep9ssl730kg0jpge1.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "C4tQLnniED3RkZnisuqwhrgj";
    private static final String API_KEY = "AIzaSyC6YNEtBw5I7JyZF0ftpPeCxPweGK-2m48";
    private static final String AUTH_SCOPE = "https://www.googleapis.com/auth/clouddevices";
    private static final String DEVICE_ID = "e08341fd-f71e-f250-3db6-6fb728579324";

    // Redirect URL for client side installed apps.
    private static final String REDIRECT_URL = "http://localhost";
    private static final File CREDENTIALS_CACHE_FILE = new File("src/main/resources/credentials_cache.json");
    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

    @Autowired
    private SectionDao sectionDao;

    /**
     * @param sectionDao
     *            the sectionDao to set
     */
    public void setSectionDao(SectionDao sectionDao)
    {
        this.sectionDao = sectionDao;
    }

    public void run()
    {
        CloudDevices apiClient;
        try
        {
            apiClient = getApiClient();
        }
        catch (IOException e)
        {
            LOG.error("An error occurred while fetching the API Client", e);
            throw new RuntimeException("Could not get API client", e);
        }

        DevicesListResponse devicesListResponse;
        try
        {
            // Will try to get only my own device

            // Listing devices, request to devices.list API method, returns a
            // list of devices
            // available to user. More details about the method:
            // https://developers.google.com/weave/v1/reference/cloud-api/devices/list
            devicesListResponse = apiClient.devices().list().execute();
        }
        catch (IOException e)
        {
            LOG.error("An error occurred while getting the device list", e);
            throw new RuntimeException("Could not list devices", e);
        }

        List<Device> devices = devicesListResponse.getDevices();

        for (Device d : devices)
        {
            if (d.getId().toString().equals(DEVICE_ID))
            {
                // System.out.println("Available device: " + d.getId());
                try
                {
                    String json = JSON_FACTORY.toPrettyString(d.getState().get("_ledflasher"));
                    LedFlasher flasher = new Gson().fromJson(json, LedFlasher.class);
                    LOG.info("sectionA:" + flasher.getSectionA());
                    LOG.info("sectionB:" + flasher.getSectionB());
                    LOG.info("sectionC:" + flasher.getSectionC());
                    
                    this.sectionDao.updateCount(1, flasher.getSectionA());
                    this.sectionDao.updateCount(2, flasher.getSectionB());
                    this.sectionDao.updateCount(3, flasher.getSectionC());                    
                }
                catch (IOException e)
                {
                    LOG.error("An error occurred while ");
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static CloudDevices getApiClient() throws IOException
    {
        // Try to load cached credentials.
        GoogleCredential credential = getCachedCredential();
        if (credential == null)
        {
            LOG.error("Did not find cached credentials");
            credential = authorize();
        }
        return new CloudDevices.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("Weave Sample")
                // .setServicePath("clouddevices/v1/e08341fd-f71e-f250-3db6-6fb728579324")
                .setServicePath("clouddevices/v1/")
                .setGoogleClientRequestInitializer(new CommonGoogleClientRequestInitializer(API_KEY)).build();
    }

    /**
     * Goes through Google OAuth2 authorization flow. See more details:
     * https://developers.google.com/weave/v1/dev-guides/getting-started/
     * authorizing
     */
    private static GoogleCredential authorize() throws IOException
    {
        // Generate the URL to send the user to grant access.
        // There are also other flows that may be used for authorization:
        // https://developers.google.com/accounts/docs/OAuth2
        String authorizationUrl = new GoogleAuthorizationCodeRequestUrl(CLIENT_ID, REDIRECT_URL,
                Collections.singleton(AUTH_SCOPE)).build();
        // Direct user to the authorization URI.
        System.out.println("Go to the following link in your browser:");
        System.out.println(authorizationUrl);
        // Get authorization code from user.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What is the authorization code?");
        String authorizationCode = in.readLine();

        // Use the authorization code to get an access token and a refresh
        // token.
        GoogleTokenResponse response = new GoogleAuthorizationCodeTokenRequest(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID,
                CLIENT_SECRET, authorizationCode, REDIRECT_URL).execute();
        cacheCredential(response);
        // Use the access and refresh tokens to set up credentials.
        GoogleCredential credential = new GoogleCredential.Builder().setJsonFactory(JSON_FACTORY)
                .setTransport(HTTP_TRANSPORT).setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
                .setFromTokenResponse(response);
        return credential;
    }

    private static GoogleCredential getCachedCredential()
    {
        try
        {
            return GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_CACHE_FILE));
        }
        catch (IOException e)
        {
            LOG.error("An error occurred while getting the credentials from cached file:", e);
            return null;
        }
    }

    private static void cacheCredential(GoogleTokenResponse response)
    {
        GenericJson json = new GenericJson();
        json.setFactory(JSON_FACTORY);
        json.put("client_id", CLIENT_ID);
        json.put("client_secret", CLIENT_SECRET);
        json.put("refresh_token",
                response.getRefreshToken() == null ? response.getAccessToken() : response.getRefreshToken());
        json.put("type", "authorized_user");
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream(CREDENTIALS_CACHE_FILE);
            out.write(json.toPrettyString().getBytes(Charset.defaultCharset()));
        }
        catch (IOException e)
        {
            LOG.error("Error caching credentials");
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                }
            }
        }
    }
}
