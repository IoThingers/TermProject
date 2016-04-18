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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import net.stupidiot.iothingers.dao.mapper.GroupRowMapper;
import net.stupidiot.iothingers.dao.mapper.UserRowMapper;
import net.stupidiot.iothingers.model.Group;
import net.stupidiot.iothingers.model.User;

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
    private static final String DELETE_USER_FROM_GROUP_USER = "DELETE FROM GROUP_USER WHERE UFID = ?";
    private static final String GET_USERS_FOR_GROUP = "SELECT U.UFID, U.USER_NAME, U.USER_MAJOR, U.USER_ACTIVE FROM USERS U, GROUP_USER GU WHERE U.UFID = GU.UFID AND GU.GROUP_ID = ?";
    private static final String GET_GROUP_BY_GROUP_ID = "SELECT G.GROUP_ID, G.GROUP_NAME, G.ROOM_ID, G.CREATOR_ID, G.COURSE_ID, R.ROOM_NAME, C.COURSE_NAME, U.USER_NAME FROM \"dbo\".\"GROUP\" G, ROOM R, COURSE C, USERS U WHERE R.ROOM_ID = G.ROOM_ID AND C.COURSE_ID = G.COURSE_ID AND U.UFID = G.CREATOR_ID AND G.GROUP_ID = ?";
    private static final String GET_ROOM_OF_GROUP = "SELECT ROOM_ID FROM \"dbo\".\"GROUP\" WHERE GROUP_ID = ?";
    private static final String GET_GROUP_BY_USER_ID = "SELECT G.GROUP_ID, G.GROUP_NAME, G.ROOM_ID, G.CREATOR_ID, G.COURSE_ID, R.ROOM_NAME, C.COURSE_NAME, U.USER_NAME FROM \"dbo\".\"GROUP\" G, ROOM R, COURSE C, USERS U, GROUP_USER GU WHERE R.ROOM_ID = G.ROOM_ID AND C.COURSE_ID = G.COURSE_ID AND U.UFID = G.CREATOR_ID AND GU.GROUP_ID = G.GROUP_ID AND GU.UFID = ?";


    @Autowired
    private RoomDao roomDao;

    /**
     * @param roomDao
     *            the roomDao to set
     */
    public void setRoomDao(RoomDao roomDao)
    {
        this.roomDao = roomDao;
    }

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

        this.roomDao.setRoomAvailability(group.getRoomId(), false);

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

        int roomId = this.getJdbcTemplate().queryForObject(GET_ROOM_OF_GROUP, Integer.class, groupId);

        LOG.info("Fetched the roomId for the groupId: " + groupId);

        int numRows = this.getJdbcTemplate().update(DELETE_GROUP, new PreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setInt(1, groupId);
            }
        });

        LOG.info("Deleted " + numRows + " group entries for groupId: " + groupId);
        LOG.info("Marking the room " + roomId + " as available");
        this.roomDao.setRoomAvailability(roomId, true);

        return numRows;
    }

    /**
     * @return
     */
    public List<Group> getAllGroups()
    {
        LOG.info("GroupDao.getAllGroups method called.");
        final List<Group> groups = this.getJdbcTemplate().query(GET_ALL_GROUPS, new GroupRowMapper(false));
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
        }, new GroupRowMapper(false));

        LOG.info("No. of groups successfully fetched: " + groups.size());
        return groups;
    }

    /**
     * @param userId
     * @param groupId
     * @return
     */
    public int deleteUserFromGroup(final int userId)
    {
        LOG.info("GroupDao.deleteUserFromGroup method called for userId: " + userId);

        int numRows = this.getJdbcTemplate().update(DELETE_USER_FROM_GROUP_USER, new PreparedStatementSetter()
        {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                ps.setInt(1, userId);
            }
        });

        LOG.info("User has been successfully deleted from the group.");

        return numRows;
    }

    /**
     * @param groupId
     * @return
     */
    public Group getGroupDetails(final int groupId)
    {
        LOG.info("GroupDao.getGroupDetails method called for groupId: " + groupId);

        final Group group = this.getJdbcTemplate().queryForObject(GET_GROUP_BY_GROUP_ID, new GroupRowMapper(true), groupId);

        LOG.info("Successfully fetched group details for the group with groupId: " + groupId);

        populateUsersInGroup(group);

        return group;
    }

    /**
     * @param group
     */
    private void populateUsersInGroup(final Group group)
    {
        LOG.info("GroupDao.populateUsersInGroup method called for group with groupId: " + group.getId());

        final List<User> members = this.getJdbcTemplate().query(GET_USERS_FOR_GROUP, new UserRowMapper(),
                group.getId());

        LOG.info("No. of users fetched for the group: " + members.size());

        group.setMembers(members);
    }

    /**
     * @param userId
     * @return
     */
    public Group getGroupDetailsByUserId(int userId)
    {
        LOG.info("GroupDao.getGroupDetails method called for userId: {}", userId);

        final Group group = this.getJdbcTemplate().queryForObject(GET_GROUP_BY_USER_ID, new GroupRowMapper(true), userId);

        LOG.info("Successfully fetched group details for the group with groupId: " + userId);

        populateUsersInGroup(group);

        return group;        
    }
}