/**
 * 
 */
package net.stupidiot.iothingers.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.stupidiot.iothingers.model.Course;

/**
 * @author Rahul
 *
 */
@RestController
@RequestMapping()
public class CourseController
{
    public List<Course> showCourses()
    {
        return null;
    }
}
