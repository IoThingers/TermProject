/**
 * 
 */
package net.stupidiot.iothingers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.stupidiot.iothingers.dao.RoomDao;
import net.stupidiot.iothingers.model.Room;

/**
 * @author Rahul
 *
 */
@Service
public class RoomService
{
    private static final Logger LOG = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private RoomDao dao;

    /**
     * @return the dao
     */
    public RoomDao getDao()
    {
        return dao;
    }

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(RoomDao dao)
    {
        this.dao = dao;
    }

    /**
     * @param roomId
     * @return
     */
    public boolean isRoomAvailable(final int roomId)
    {
        LOG.info("RoomService.isRoomAvailale called");
        return this.dao.isRoomAvailable(roomId);
    }

    /**
     * @param roomId
     * @return
     */
    public Room getRoomDetails(int roomId)
    {
        LOG.info("RoomService.getRoomDetails called.");
        return this.dao.getRoomDetails(roomId);
    }
}