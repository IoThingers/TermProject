/**
 * 
 */
package net.stupidiot.iothingers.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import net.stupidiot.iothingers.model.User;

/**
 * @author Rahul
 *
 */
@Repository
public class UserDao
{
    private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);
    
    private static final String INSERT_USER = "INSERT INTO USERS(UFID, USER_NAME, USER_MAJOR, USER_ACTIVE) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_USER_ACTIVITY = "UPDATE USERS SET USER_ACTIVE = ? WHERE UFID = ?";
    
    private JdbcTemplate jdbcTemplate;

    /**
     * @return the jdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    /**
     * @param jdbcTemplate
     *            the jdbcTemplate to set
     */
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 
     * @param user
     * @return
     */
    public int insertUser(final User user)
    {
        LOG.info("UserDao.insertUser called");
        
        final int numRows = this.jdbcTemplate.update(INSERT_USER, new PreparedStatementSetter()
        {            
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setInt(1, user.getUfid());
                ps.setString(2, user.getName());
                ps.setString(3, user.getMajor());
                ps.setBoolean(4, user.isActive());
            }
        });
        
        LOG.info("Number of rows inserted is: " + numRows);
        
        return numRows;
    }

    /**
     * @param userId
     * @param isActive
     * @return
     */
    public boolean updateUserActivity(final int userId, final boolean isActive)
    {
        int numRows = this.jdbcTemplate.update(UPDATE_USER_ACTIVITY, new PreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setBoolean(1, isActive);
                ps.setInt(2, userId);
            }
        });
        
        return numRows != 0;
    }
}