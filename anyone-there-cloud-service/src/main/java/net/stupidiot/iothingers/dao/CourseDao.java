/**
 * 
 */
package net.stupidiot.iothingers.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import net.stupidiot.iothingers.dao.mapper.CourseRowMapper;
import net.stupidiot.iothingers.model.Course;
import net.stupidiot.iothingers.service.CourseService;

/**
 * @author Rahul
 *
 */
@Repository
public class CourseDao extends JdbcTemplateDao
{
    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

    private static final String GET_ALL_COURSES = "SELECT COURSE_ID, COURSE_NAME FROM COURSE";
    private static final String GET_COURSE_BY_USER = "SELECT C.COURSE_ID, C.COURSE_NAME FROM COURSE C, USERS_COURSE UC WHERE UC.UFID = ? AND C.COURSE_ID = UC.COURSE_ID";

    /**
     * @return
     */
    public List<Course> getAllCourses()
    {
        LOG.info("CourseDao.getAllCourses called");

        final List<Course> courses = this.getJdbcTemplate().query(GET_ALL_COURSES, new CourseRowMapper());

        LOG.info("Number of courses fetched: " + courses.size());

        return courses;
    }

    /**
     * @param userId
     * @return
     */
    public List<Course> getCourseByUser(final int userId)
    {
        LOG.info("CourseDao.getCourseByUser called");        
        LOG.info("Querying the courses for UFID: " + userId);

        final List<Course> courses = this.getJdbcTemplate().query(GET_COURSE_BY_USER, new CourseRowMapper(), userId);

        LOG.info("Number of courses fetched: " + courses.size());

        return courses;
    }
}