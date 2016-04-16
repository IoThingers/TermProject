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

/**
 * @author Rahul
 *
 */
@Repository
public class RoomDao extends JdbcTemplateDao
{
    private static final Logger LOG = LoggerFactory.getLogger(RoomDao.class);
    
    private static final String GET_ROOM_AVAILABILITY = "SELECT AVAILABILITY FROM ROOM WHERE ROOM_ID = ?";
    
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
}