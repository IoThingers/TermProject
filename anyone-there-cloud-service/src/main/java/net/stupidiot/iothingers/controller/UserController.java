/**
 * 
 */
package net.stupidiot.iothingers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.stupidiot.iothingers.model.User;
import net.stupidiot.iothingers.response.ResponseType;
import net.stupidiot.iothingers.response.RestResponse;
import net.stupidiot.iothingers.service.UserService;

/**
 * @author Rahul
 *
 */
@RestController
@RequestMapping(path = "/users")
public class UserController
{
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    /**
     * @return the service
     */
    public UserService getService()
    {
        return this.service;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(final UserService service)
    {
        this.service = service;
    }

    /**
     * 
     * @param user
     * @return
     */
    @RequestMapping(path = "/create-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<Boolean> createUser(@RequestBody final User user)
    {
        LOG.info("Create user method called for userId: " + user.getUfid());
        LOG.info("The input received is:" + user.getName());

        final RestResponse<Boolean> response = new RestResponse<>();

        try
        {
            boolean created = this.service.createUser(user);
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.NUMBER);
            response.setResponse(created);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while creating the user: ", e);
            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }

        return response;
    }

    /**
     * 
     * @param userId
     * @return
     */
    @RequestMapping(path = "/delete-user", method = RequestMethod.DELETE)
    public RestResponse<Boolean> deleteUser(final int userId)
    {
        final RestResponse<Boolean> response = new RestResponse<>();
        return response;
    }

    /**
     * 
     * @param userId
     * @param isActive
     * @return
     */
    @RequestMapping(path = "/set-user-activity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RestResponse<Boolean> setUserActivity(@RequestParam(value = "user-id") final int userId, @RequestParam(value = "is-active") final boolean isActive)
    {
        LOG.info("UserController.setUserActivity method called for userId: " + userId);
        
        final RestResponse<Boolean> response = new RestResponse<>();
        
        try
        {
            final boolean updated = this.service.updateUserActivity(userId, isActive);            
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.BOOLEAN);
            response.setResponse(updated);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while setting activity of user: " + userId, e);
            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.BOOLEAN);
            response.setResponse(null);
        }
        
        return response;
    }
    
    /**
     * 
     * @param userId
     * @return
     */
    @RequestMapping(path = "/get-friends-of-user", method = RequestMethod.GET)
    public RestResponse<List<User>> getUserFriends(@RequestParam(value = "user-id") final int userId)
    {
        LOG.info("UserController.getUserFriends method called for userId: " + userId);
        
        final RestResponse<List<User>> response = new RestResponse<>();
        
        try
        {
            final List<User> friends = this.service.getFriendsOfUser(userId);
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.LIST);
            response.setResponse(friends);
            
            LOG.info("Successfully fetched friends for userId: " + userId);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while fetching friends of user: " + userId, e);
            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.LIST);
            response.setResponse(null);
        }
        
        return response;
    }
}