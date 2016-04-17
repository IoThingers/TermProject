/**
 * 
 */
package net.stupidiot.iothingers.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.stupidiot.iothingers.model.Room;

/**
 * @author Rahul
 *
 */
public class RoomRowMapper implements RowMapper<Room>
{
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public Room mapRow(ResultSet rs, int numRows) throws SQLException
    {
        final Room room = new Room();
        
        room.setId(rs.getInt(1));
        room.setName(rs.getString(2));
        room.setLibraryId(rs.getInt(3));
        room.setAvailable(rs.getBoolean(4));
        room.setLevel(rs.getInt(5));
        
        return room;
    }    
}