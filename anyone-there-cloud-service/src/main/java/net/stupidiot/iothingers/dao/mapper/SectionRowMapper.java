/**
 * 
 */
package net.stupidiot.iothingers.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.stupidiot.iothingers.model.Section;

/**
 * @author Rahul
 *
 */
public class SectionRowMapper implements RowMapper<Section>
{
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public Section mapRow(ResultSet rs, int numRows) throws SQLException
    {
        final Section section = new Section();
        
        section.setId(rs.getInt(1));
        section.setCapacity(rs.getInt(2));
        section.setCount(rs.getInt(3));
        section.setName(rs.getString(4));
        section.setLibraryId(rs.getInt(5));
        section.setLevel(rs.getInt(6));
        
        return section;
    }
}
