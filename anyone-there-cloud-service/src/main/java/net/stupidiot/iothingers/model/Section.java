/**
 * 
 */
package net.stupidiot.iothingers.model;

/**
 * @author Rahul
 *
 */
public class Section
{
    private int id;
    private int capacity;
    private int count;
    private String name;
    private int libraryId;
    private int level;

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
     * @return the capacity
     */
    public int getCapacity()
    {
        return capacity;
    }

    /**
     * @param capacity
     *            the capacity to set
     */
    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    /**
     * @return the count
     */
    public int getCount()
    {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(int count)
    {
        this.count = count;
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

}
