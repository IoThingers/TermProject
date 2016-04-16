/**
 * 
 */
package net.stupidiot.iothingers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import net.stupidiot.iothingers.dao.mapper.GroupRowMapper;
import net.stupidiot.iothingers.model.Group;

/**
 * @author Rahul
 *
 */
@Repository
public class GroupDao extends JdbcTemplateDao
{
    private static final Logger LOG = LoggerFactory.getLogger(GroupDao.class);

    private static final String INSERT_GROUP = "INSERT INTO \"dbo\".\"GROUP\"(GROUP_NAME, ROOM_ID, CREATOR_ID, COURSE_ID) VALUES(?, ?, ?, ?)";
    private static final String DELETE_GROUP = "DELETE FROM \"dbo\".\"GROUP\" WHERE GROUP_ID = ?";
    private static final String GET_ALL_GROUPS = "SELECT GROUP_ID, GROUP_NAME, ROOM_ID, CREATOR_ID, COURSE_ID FROM \"dbo\".\"GROUP\"";
    private static final String GET_GROUPS_FOR_COURSE = "SELECT GROUP_ID, GROUP_NAME, ROOM_ID, CREATOR_ID, COURSE_ID FROM \"dbo\".\"GROUP\" WHERE COURSE_ID = ?";
    private static final String INSERT_USER_IN_GROUP_USER = "INSERT INTO GROUP_USER VALUES(?, ?)";
    private static final String DELETE_USER_FROM_GROUP_USER = "DELETE FROM GROUP_USER WHERE UFID = ? AND GROUP_ID = ?";
    
    /**
     * @param group
     * @return
     */
    public int createGroup(final Group group)
    {
        LOG.info("GroupDao.createGroup called with group name: " + group.getName());
        
        final KeyHolder holder = new GeneratedKeyHolder();
        
        int numRows = this.getJdbcTemplate().update(new PreparedStatementCreator()
        {            
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
            {
                final PreparedStatement ps = connection.prepareStatement(INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, group.getName());
                ps.setInt(2, group.getRoomId());
                ps.setInt(3, group.getCreatorId());
                ps.setInt(4, group.getCourseId());
                
                return ps;
            }
        }, holder);
        
        LOG.info("No. of rows inserted: " + numRows);
        LOG.info("The group id generated is " + holder.getKey().intValue());
        
        this.addUserToGroup(group.getCreatorId(), holder.getKey().intValue());        
        return holder.getKey().intValue();
    }

    /**
     * @param creatorId
     * @param intValue
     */
    public int addUserToGroup(final int creatorId, final int groupId)
    {
        LOG.info("GroupDao.addUserToGroup method called for userId: " + creatorId + " and groupId: " + groupId);
        
        int numRows = this.getJdbcTemplate().update(INSERT_USER_IN_GROUP_USER, new PreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setInt(1, groupId);
                ps.setInt(2, creatorId);
            }
        });
        
        LOG.info("User has been successfully inserted in the group.");
        
        return numRows;
    }

    /**
     * @param groupId
     * @return
     */
    public int deleteGroup(final int groupId)
    {
        LOG.info("GroupDao.deleteGroup method called for groupId: " + groupId);
        
        int numRows = this.getJdbcTemplate().update(DELETE_GROUP, new PreparedStatementSetter()
        {            
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setInt(1, groupId);
            }
        });
        
        LOG.info("Deleted " + numRows + "group entries for groupId: " + groupId);
        
        return numRows;
    }

    /**
     * @return
     */
    public List<Group> getAllGroups()
    {
        LOG.info("GroupDao.getAllGroups method called.");
        final List<Group> groups = this.getJdbcTemplate().query(GET_ALL_GROUPS, new GroupRowMapper());
        LOG.info("No. of groups successfully fetched: " + groups.size());
        return groups;
    }

    /**
     * @param courseId
     * @return
     */
    public List<Group> getGroupsForCourse(final int courseId)
    {
        LOG.info("GroupDao.getGroupsForCourse method called for courseId: " + courseId);
        final List<Group> groups = this.getJdbcTemplate().query(GET_GROUPS_FOR_COURSE, new PreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setInt(1, courseId);
            }
        }, new GroupRowMapper());
        
        LOG.info("No. of groups successfully fetched: " + groups.size());
        return groups;
    }

    /**
     * @param userId
     * @param groupId
     * @return
     */
    public int deleteUserFromGroup(final int userId, final int groupId)
    {
        LOG.info("GroupDao.deleteUserFromGroup method called for userId: " + userId + " and groupId: " + groupId);
        
        int numRows = this.getJdbcTemplate().update(DELETE_USER_FROM_GROUP_USER, new PreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setInt(1, userId);
                ps.setInt(2, groupId);
            }
        });
        
        LOG.info("User has been successfully deleted from the group.");
        
        return numRows;
    }
}