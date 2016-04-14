/**
 * 
 */
package net.stupidiot.iothingers.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.stupidiot.iothingers.model.User;

/**
 * @author Rahul
 *
 */
public class UserRowMapper implements RowMapper<User>
{
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        final User user = new User();
        
        user.setUfid(rs.getInt("UFID"));
        user.setName(rs.getString("USER_NAME"));
        user.setMajor(rs.getString("USER_MAJOR"));
        user.setActive(rs.getBoolean("USER_ACTIVE"));
        
        return user;
    }

}
