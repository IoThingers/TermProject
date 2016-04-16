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
    private int id;
    private String name;
    private List<User> members;
    private int roomId;
    private int courseId;
    private int creatorId;

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id)
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
    public int getRoomId()
    {
        return roomId;
    }

    /**
     * @param roomId
     *            the roomId to set
     */
    public void setRoomId(int roomId)
    {
        this.roomId = roomId;
    }

    /**
     * @return the courseId
     */
    public int getCourseId()
    {
        return courseId;
    }

    /**
     * @param courseId
     *            the courseId to set
     */
    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    /**
     * @return the creatorId
     */
    public int getCreatorId()
    {
        return creatorId;
    }

    /**
     * @param creatorId
     *            the creatorId to set
     */
    public void setCreatorId(int creatorId)
    {
        this.creatorId = creatorId;
    }
}
