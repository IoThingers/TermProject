/**
 * 
 */
package net.stupidiot.iothingers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import net.stupidiot.iothingers.dao.UserDao;
import net.stupidiot.iothingers.exception.UserExistsException;
import net.stupidiot.iothingers.model.User;

/**
 * @author Rahul
 *
 */
@Component
@ComponentScan
public class UserService
{
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
    public int createUser(final User user)// throws UserExistsException
    {
        return this.dao.insertUser(user);
    }
}