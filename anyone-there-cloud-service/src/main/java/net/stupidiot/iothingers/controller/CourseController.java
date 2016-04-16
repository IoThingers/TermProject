/**
 * 
 */
package net.stupidiot.iothingers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.stupidiot.iothingers.model.Course;
import net.stupidiot.iothingers.response.ResponseType;
import net.stupidiot.iothingers.response.RestResponse;
import net.stupidiot.iothingers.service.CourseService;

/**
 * @author Rahul
 *
 */
@RestController
@RequestMapping(path = "/courses")
public class CourseController
{
    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService service;

    /**
     * @return the service
     */
    public CourseService getService()
    {
        return service;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(CourseService service)
    {
        this.service = service;
    }

    @RequestMapping(path = "/show-all-courses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<List<Course>> showAllCourses()
    {
        LOG.info("CourseController.showAllCourses method called.");

        final RestResponse<List<Course>> response = new RestResponse<>();

        try
        {
            List<Course> courses = this.service.getAllCourses();
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.LIST);
            response.setResponse(courses);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while fetching the courses: ", e);
            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }

        return response;
    }
    
    @RequestMapping(path = "/show-courses-of-user", method = RequestMethod.GET)
    public RestResponse<List<Course>> getUserCourses(@RequestParam(value = "user-id") final int userId)
    {
        LOG.info("CourseController.getUserCourses method called.");

        final RestResponse<List<Course>> response = new RestResponse<>();

        try
        {
            List<Course> courses = this.service.getCourseByUser(userId);
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.LIST);
            response.setResponse(courses);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while fetching the courses: ", e);
            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }

        return response;        
    }
}