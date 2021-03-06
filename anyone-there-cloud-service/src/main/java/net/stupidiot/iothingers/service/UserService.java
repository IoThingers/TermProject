/**
 * 
 */
package net.stupidiot.iothingers.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.stupidiot.iothingers.dao.UserDao;
import net.stupidiot.iothingers.exception.UserExistsException;
import net.stupidiot.iothingers.model.User;

/**
 * @author Rahul
 *
 */
@Service
public class UserService
{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserDao dao;

    /**
     * @return the dao
     */
    public UserDao getDao()
    {
        return this.dao;
    }

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(final UserDao dao)
    {
        this.dao = dao;
    }

    /**
     * 
     * @param user
     * @return
     * @throws UserExistsException
     */
    public boolean createUser(final User user)
    {
        LOG.info("UserService.createUser called.");
        return this.dao.insertUser(user) == 1;
    }
    
    /**
     * 
     * @param userId
     * @param isActive
     * @return
     */
    public boolean updateUserActivity(final int userId, final boolean isActive)
    {
        LOG.info("UserService.updateUserActivity called");
        return this.dao.updateUserActivity(userId, isActive) == 1;
    }

    /**
     * @param userId
     * @return
     */
    public List<User> getFriendsOfUser(int userId)
    {
        LOG.info("UserService.getFriendsOfUser called");
        return this.dao.getFriendsOfUser(userId);
    }
}