/**
 * 
 */
package net.stupidiot.iothingers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.stupidiot.iothingers.model.Room;
import net.stupidiot.iothingers.response.ResponseType;
import net.stupidiot.iothingers.response.RestResponse;
import net.stupidiot.iothingers.service.RoomService;

/**
 * @author Rahul
 *
 */
@RestController
@RequestMapping(path = "/rooms")
public class RoomController
{
    private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService service;

    /**
     * @return the service
     */
    public RoomService getService()
    {
        return service;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(RoomService service)
    {
        this.service = service;
    }

    /**
     * 
     * @return
     */
    public List<Room> showRooms()
    {
        return null;
    }

    public List<Room> showAvailableRooms()
    {
        return null;
    }

    public boolean setRoomAvailable(int roomId, boolean isAvailable)
    {
        return false;
    }

    /**
     * 
     * @param roomId
     * @return
     */
    @RequestMapping(path = "/is-room-available", method = RequestMethod.GET)
    public RestResponse<Boolean> isRoomAvailable(@RequestParam(name = "room-id") final int roomId)
    {
        LOG.info("RoomController.isRoomAvailable called for room-id: " + roomId);

        final RestResponse<Boolean> response = new RestResponse<>();

        try
        {
            boolean isAvailable = this.service.isRoomAvailable(roomId);
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.BOOLEAN);
            response.setResponse(isAvailable);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while fetching the availability of room with id: " + roomId + ": ", e);
            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }

        return response;
    }
}