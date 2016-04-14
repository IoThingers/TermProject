/**
 * 
 */
package net.stupidiot.iothingers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import net.stupidiot.iothingers.dao.mapper.UserRowMapper;
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
    private static final String GET_USER_DETAILS = "SELECT UFID, USER_NAME, USER_MAJOR, USER_ACTIVE FROM USERS WHERE UFID IN (:ufids)";
    private static final String GET_USER_FRIENDS = "SELECT USERS_1, USERS_2 FROM FRIENDSHIP WHERE USERS_2 = ? OR USERS_1 = ?";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
     * @return the namedParameterJdbcTemplate
     */
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate()
    {
        return namedParameterJdbcTemplate;
    }

    /**
     * @param namedParameterJdbcTemplate
     *            the namedParameterJdbcTemplate to set
     */
    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

        LOG.info("Number of rows inserted: " + numRows);

        return numRows;
    }

    /**
     * @param userId
     * @param isActive
     * @return
     */
    public int updateUserActivity(final int userId, final boolean isActive)
    {
        LOG.info("UserDao.updateUserActivity called");
        
        int numRows = this.jdbcTemplate.update(UPDATE_USER_ACTIVITY, new PreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setBoolean(1, isActive);
                ps.setInt(2, userId);
            }
        });

        LOG.info("Number of rows updated: " + numRows);
        
        return numRows;
    }

    /**
     * @param userId
     * @return
     */
    public List<User> getFriendsOfUser(final int userId)
    {
        LOG.info("UserDao.getFriendsOfUser called");
        
        final List<Integer> friendIds = this.jdbcTemplate.query(GET_USER_FRIENDS, new PreparedStatementSetter()
        {
            /*
             * (non-Javadoc)
             * @see org.springframework.jdbc.core.PreparedStatementSetter#setValues(java.sql.PreparedStatement)
             */
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setInt(1, userId);
                ps.setInt(2, userId);
            }
        },
        new RowMapper<Integer>()
        {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                return rs.getInt(1) == userId ? rs.getInt(2) : rs.getInt(1);
            }            
        });
        
        LOG.info("Number of friends for the user : " + userId + " is " + friendIds.size());
        
        return friendIds.size() == 0 ? Collections.emptyList() : this.getUserDetails(friendIds);
    }
    
    /**
     * 
     * @param userIds
     * @return
     */
    public List<User> getUserDetails(List<Integer> userIds)
    {
        LOG.info("UserDao.getUserDetails");
        
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ufids", userIds);
        
        final List<User> users = this.namedParameterJdbcTemplate.query(GET_USER_DETAILS, paramMap, new UserRowMapper());
        
        LOG.info("Number of users fetched for the " + userIds.size() + " is " + users.size());
        
        return users;
    }
}