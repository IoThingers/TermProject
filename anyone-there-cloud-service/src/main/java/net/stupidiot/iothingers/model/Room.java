/**
 * 
 */
package net.stupidiot.iothingers.model;

/**
 * @author Rahul
 *
 */
public class Room
{
    private int id;
    private String name;
    private int level;
    private boolean available;
    private int libraryId;

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
     * @return the level
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * @return the available
     */
    public boolean isAvailable()
    {
        return available;
    }

    /**
     * @param available
     *            the available to set
     */
    public void setAvailable(boolean available)
    {
        this.available = available;
    }

    /**
     * @return the libraryId
     */
    public int getLibraryId()
    {
        return libraryId;
    }

    /**
     * @param libraryId
     *            the libraryId to set
     */
    public void setLibraryId(int libraryId)
    {
        this.libraryId = libraryId;
    }
}