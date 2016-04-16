/**
 * 
 */
package net.stupidiot.iothingers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.stupidiot.iothingers.model.Group;
import net.stupidiot.iothingers.response.ResponseType;
import net.stupidiot.iothingers.response.RestResponse;
import net.stupidiot.iothingers.service.GroupService;

/**
 * @author Rahul
 *
 */
@RestController
@RequestMapping(path = "/groups")
public class GroupController
{
    private static final Logger LOG = LoggerFactory.getLogger(GroupController.class);
    
    @Autowired
    private GroupService service;

    /**
     * @return the service
     */
    public GroupService getService()
    {
        return service;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(GroupService service)
    {
        this.service = service;
    }

    /**
     * 
     * @param group
     * @return
     */
    @RequestMapping(path = "/create-group", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<Integer> createGroup(final Group group)
    {
        LOG.info("GroupController.createGroup called for group: " + group.getName());

        final RestResponse<Integer> response = new RestResponse<>();

        try
        {
            int groupId = this.service.createGroup(group);

            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.NUMBER);
            response.setResponse(groupId);

            LOG.info("Group created successfully with groupId: " + groupId);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while creating the group: ", e);

            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }
        
        return response;
    }

    /**
     * 
     * @param groupId
     * @return
     */
    @RequestMapping(path = "/delete-group", method = RequestMethod.DELETE)
    public RestResponse<Boolean> deleteGroup(@RequestParam(value = "group-id") final int groupId)
    {
        LOG.info("GroupController.deleteGroup called for groupId: " + groupId);

        final RestResponse<Boolean> response = new RestResponse<>();

        try
        {
            boolean deleted = this.service.deleteGroup(groupId);

            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.BOOLEAN);
            response.setResponse(deleted);

            if(deleted)
            {
                LOG.info("Group deleted with groupId: " + groupId);
            }
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while deleting the group: ", e);

            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }
        
        return response;
    }

    @RequestMapping(path = "/add-user-to-group", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public boolean addUserToGroup(@RequestParam(value = "user-id") final String userId,
            @RequestParam(value = "group-id") final String groupId)
    {
        return false;
    }

    @RequestMapping(path = "/delete-user-from-group", method = RequestMethod.GET)
    public boolean deleteUserFromGroup(@RequestParam(value = "user-id") final String userId,
            @RequestParam(value = "group-id") final String groupId)
    {
        return false;
    }

    /**
     * 
     * @param courseId
     * @return
     */
    @RequestMapping(path = "/show-groups", method = RequestMethod.GET)
    public RestResponse<List<Group>> showGroups(@RequestParam(value = "course-id") final int courseId)
    {
        LOG.info("GroupController.showGroups method called");

        final RestResponse<List<Group>> response = new RestResponse<>();

        try
        {
            List<Group> groups = this.service.getAllGroups();

            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.LIST);
            response.setResponse(groups);
            
            LOG.info("No. of groups successfully fetched : " + groups.size());
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while fetching all the groups: ", e);

            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }
        
        return response;
    }
}