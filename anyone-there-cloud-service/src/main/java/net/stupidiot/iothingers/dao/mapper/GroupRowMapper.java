/**
 * 
 */
package net.stupidiot.iothingers.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.stupidiot.iothingers.model.Group;

/**
 * @author Rahul
 *
 */
public class GroupRowMapper implements RowMapper<Group>
{
    private boolean extra;
    
    /**
     * 
     */
    public GroupRowMapper(boolean extra)
    {
        this.extra = extra;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public Group mapRow(ResultSet rs, int arg1) throws SQLException
    {
        final Group group = new Group();
        
        group.setId(rs.getInt(1));
        group.setName(rs.getString(2));
        group.setRoomId(rs.getInt(3));
        group.setCreatorId(rs.getInt(4));
        group.setCourseId(rs.getInt(5));
        
        if(this.extra)
        {
            group.setRoomName(rs.getString(6));
            group.setCourseName(rs.getString(7));
            group.setCreatorName(rs.getString(8));
        }        
        
        return group;
    }
}