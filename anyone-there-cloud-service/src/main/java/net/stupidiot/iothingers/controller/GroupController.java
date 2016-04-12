/**
 * 
 */
package net.stupidiot.iothingers.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.stupidiot.iothingers.model.Group;

/**
 * @author Rahul
 *
 */
@RestController
@RequestMapping(path="/groups")
public class GroupController
{
    @RequestMapping(path="/create-group", method=RequestMethod.POST, consumes="application/json")
    public int createGroup(Group group)
    {
        return -1;
    }
    
    @RequestMapping(path="/delete-group", method=RequestMethod.DELETE)
    public boolean deleteGroup(@RequestParam(value="group-id") final int groupId)
    {
        return false;
    }
    
    @RequestMapping(path="/add-user-to-group", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
    public boolean addUserToGroup(@RequestParam(value="user-id") final String userId, @RequestParam(value="group-id") final String groupId)
    {
        return false;
    }

    @RequestMapping(path="/delete-user-from-group", method=RequestMethod.GET)
    public boolean deleteUserFromGroup(@RequestParam(value="user-id") final String userId, @RequestParam(value="group-id") final String groupId)
    {
        return false;
    }
    
    @RequestMapping(path="/show-groups",method=RequestMethod.GET)
    public List<Group> showGroups(@RequestParam(value="course-id") final int courseId)
    {
        return null;
    }
}
