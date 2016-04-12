/**
 * 
 */
package net.stupidiot.iothingers.model;

import java.util.List;

/**
 * @author Rahul
 *
 */
public class User
{
    private String id;
    private String name;
    private String major;
    private List<Course> courses;

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the major
     */
    public String getMajor()
    {
        return major;
    }

    /**
     * @param major
     *            the major to set
     */
    public void setMajor(String major)
    {
        this.major = major;
    }

    /**
     * @return the courses
     */
    public List<Course> getCourses()
    {
        return courses;
    }

    /**
     * @param courses
     *            the courses to set
     */
    public void setCourses(List<Course> courses)
    {
        this.courses = courses;
    }
}
