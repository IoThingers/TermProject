/**
 * 
 */
package net.stupidiot.iothingers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
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
@Component
@ComponentScan
@RestController
@RequestMapping(path = "/users")
public class UserController
{
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

    @RequestMapping(path = "/create-user", method = RequestMethod.POST, consumes = "application/json")
    public RestResponse<Integer> createUser(final User user)
    {
        final RestResponse<Integer> response = new RestResponse<>();

        try
        {
            int userId = service.createUser(user);
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.NUMBER);
            response.setResponse(userId);
        }
        catch (Exception e)
        {
            response.setResponseCode(401);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }

        return response;
    }

    @RequestMapping(path = "/delete-user", method = RequestMethod.DELETE, consumes = "application/json")
    public RestResponse<Boolean> deleteUser(final int userId)
    {
        final RestResponse<Boolean> response = new RestResponse<>();
        return response;
    }

    @RequestMapping(path = "/set-user-activity", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public RestResponse<Boolean> setUserActivity(@RequestParam(value = "user-id") final int userId,
            @RequestParam(value = "is-active") final boolean isActive)
    {
        final RestResponse<Boolean> response = new RestResponse<>();
        return response;
    }
}