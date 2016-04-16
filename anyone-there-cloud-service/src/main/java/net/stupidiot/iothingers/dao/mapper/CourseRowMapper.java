/**
 * 
 */
package net.stupidiot.iothingers.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.stupidiot.iothingers.model.Course;

/**
 * @author Rahul
 *
 */
public class CourseRowMapper implements RowMapper<Course>
{
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
     * int)
     */
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        final Course course = new Course();
        
        course.setId(rs.getInt(1));
        course.setName(rs.getString(2));
        
        return course;
    }
}