/**
 * 
 */
package net.stupidiot.iothingers.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.stupidiot.iothingers.dao.CourseDao;
import net.stupidiot.iothingers.model.Course;

/**
 * @author Rahul
 *
 */
@Service
public class CourseService
{
    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseDao dao;

    /**
     * @return the dao
     */
    public CourseDao getDao()
    {
        return dao;
    }

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(CourseDao dao)
    {
        this.dao = dao;
    }

    /**
     * 
     * @return
     */
    public List<Course> getAllCourses()
    {
        LOG.info("CourseService.getAllCourses called.");
        return this.dao.getAllCourses();
    }

    /**
     * @param userId
     * @return
     */
    public List<Course> getCourseByUser(final int userId)
    {
        LOG.info("CourseService.getCourseByUser called.");
        return this.dao.getCourseByUser(userId);
    }
}