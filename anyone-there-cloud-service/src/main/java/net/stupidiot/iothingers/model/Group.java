/**
 * 
 */
package net.stupidiot.iothingers.model;

import java.util.List;

/**
 * @author Rahul
 *
 */
public class Group
{
    private String id;
    private String name;
    private User creator;
    private List<User> members;
    private String roomId;
    private String courseId;

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
     * @return the creator
     */
    public User getCreator()
    {
        return creator;
    }

    /**
     * @param creator
     *            the creator to set
     */
    public void setCreator(User creator)
    {
        this.creator = creator;
    }

    /**
     * @return the members
     */
    public List<User> getMembers()
    {
        return members;
    }

    /**
     * @param members
     *            the members to set
     */
    public void setMembers(List<User> members)
    {
        this.members = members;
    }

    /**
     * @return the roomId
     */
    public String getRoomId()
    {
        return roomId;
    }

    /**
     * @param roomId
     *            the roomId to set
     */
    public void setRoomId(String roomId)
    {
        this.roomId = roomId;
    }

    /**
     * @return the courseId
     */
    public String getCourseId()
    {
        return courseId;
    }

    /**
     * @param courseId
     *            the courseId to set
     */
    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }
}
