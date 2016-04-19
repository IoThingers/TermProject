/**
 * 
 */
package net.stupidiot.iothingers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import net.stupidiot.iothingers.dao.mapper.RoomRowMapper;
import net.stupidiot.iothingers.model.Room;

/**
 * @author Rahul
 *
 */
@Repository
public class RoomDao extends JdbcTemplateDao
{
    private static final Logger LOG = LoggerFactory.getLogger(RoomDao.class);
    
    private static final String GET_ROOM_AVAILABILITY = "SELECT AVAILABILITY FROM ROOM WHERE ROOM_ID = ?";
    private static final String SET_ROOM_AVAILABILITY = "UPDATE ROOM SET AVAILABILITY = ? WHERE ROOM_ID = ?";
    private static final String GET_ROOM_DETAILS = "SELECT ROOM_ID, ROOM_NAME, LIBRARY_ID, AVAILABILITY, LEVEL FROM ROOM WHERE ROOM_ID = ?";
    
    /**
     * @param roomId
     * @return
     */
    public boolean isRoomAvailable(final int roomId)
    {
        LOG.info("RoomDao.isRoomAvailable called for roomId: " + roomId);
        final boolean isAvailable = this.getJdbcTemplate().query(GET_ROOM_AVAILABILITY, new ResultSetExtractor<Boolean>()
        {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                if(rs.next())
                {
                    return rs.getBoolean(1);
                }
                else
                {
                    return false;
                }
            }            
        }, roomId);
        
        LOG.info("The availability of the room " + roomId + " is " + isAvailable);        
        return isAvailable;
    }
    
    /**
     * 
     * @param roomId
     * @param isAvailable
     * @return
     */
    public int setRoomAvailability(int roomId, boolean isAvailable)
    {
        LOG.info("RoomDao.setRoomAvailability method called with roomId: " + roomId + " availability: " + isAvailable);
        
        int numRows = this.getJdbcTemplate().update(SET_ROOM_AVAILABILITY, isAvailable, roomId);
        
        LOG.info("Successfully updated the availability of roomId: " + roomId);
        
        return numRows;
        
    }

    /**
     * @param roomId
     * @return
     */
    public Room getRoomDetails(final int roomId)
    {
        LOG.info("RoomDao.getRoomDetails method called for roomId: " + roomId);
        
        final Room room = this.getJdbcTemplate().queryForObject(GET_ROOM_DETAILS, new RoomRowMapper(), roomId);
        
        LOG.info("Successfully fetched details for the room with id: " + roomId);
        
        return room;
    }
}