/**
 * 
 */
package net.stupidiot.iothingers.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Rahul
 *
 */
public class IntegerRowMapper implements RowMapper<Integer>
{
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return rs.getInt(1);        
    }

}
