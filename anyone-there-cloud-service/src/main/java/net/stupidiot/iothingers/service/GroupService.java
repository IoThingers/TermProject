/**
 * 
 */
package net.stupidiot.iothingers.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.stupidiot.iothingers.dao.GroupDao;
import net.stupidiot.iothingers.model.Group;

/**
 * @author Rahul
 *
 */
@Service
public class GroupService
{
    private static final Logger LOG = LoggerFactory.getLogger(GroupService.class);
    
    @Autowired
    private GroupDao dao;

    /**
     * @return the dao
     */
    public GroupDao getDao()
    {
        return dao;
    }

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(GroupDao dao)
    {
        this.dao = dao;
    }

    /**
     * @param group
     * @return
     */
    public int createGroup(final Group group)
    {
        LOG.info("GroupService.createGroup method called.");
        return this.dao.createGroup(group);
    }

    /**
     * @param groupId
     * @return
     */
    public boolean deleteGroup(int groupId)
    {
        LOG.info("GroupService.deleteGroup method called.");
        return this.dao.deleteGroup(groupId) == 1;
    }

    /**
     * @param userId
     * @param groupId
     * @return
     */
    public boolean addUserToGroup(int userId, int groupId)
    {
        LOG.info("GroupService.addUserToGroup method called.");
        return this.dao.addUserToGroup(userId, groupId) == 1;
    }

    /**
     * @return
     */
    public List<Group> getAllGroups()
    {
        LOG.info("GroupService.getAllGroups method called.");
        return this.dao.getAllGroups();
    }

    /**
     * @param courseId
     * @return
     */
    public List<Group> getGroupsForCourse(int courseId)
    {
        LOG.info("GroupService.getGroupsForCourse method called.");
        return this.dao.getGroupsForCourse(courseId);
    }

    /**
     * @param userId
     * @param groupId
     * @return
     */
    public boolean deleteUserFromGroup(int userId)
    {
        LOG.info("GroupService.deleteUserFromGroup method called.");
        return this.dao.deleteUserFromGroup(userId) == 1;
    }

    /**
     * @param groupId
     * @return
     */
    public Group getGroupDetails(int groupId)
    {
        LOG.info("GroupService.getGroupDetails method called.");
        return this.dao.getGroupDetails(groupId);
    }
}