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

import net.stupidiot.iothingers.model.Section;
import net.stupidiot.iothingers.response.ResponseType;
import net.stupidiot.iothingers.response.RestResponse;
import net.stupidiot.iothingers.service.SectionService;

/**
 * @author Rahul
 *
 */
@RestController
@RequestMapping(path = "/sections")
public class SectionController
{
    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    private SectionService service;

    /**
     * @return the service
     */
    public SectionService getService()
    {
        return service;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(SectionService service)
    {
        this.service = service;
    }

    /**
     * 
     * @param sectionId
     * @return
     */
    @RequestMapping(path = "/update-section-count", method = RequestMethod.POST)
    public RestResponse<Boolean> updateCount(@RequestParam(name = "section-id") final int sectionId,
            @RequestParam(name = "count") final int count)
    {
        LOG.info("SectionController.updateCount method called for sectionId {} and count {}", sectionId, count);

        RestResponse<Boolean> response = new RestResponse<>();

        try
        {
            boolean updated = this.service.updateCount(sectionId, count);
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.BOOLEAN);
            response.setResponse(updated);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while updating the count of the section with id {}", sectionId, e);
            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }

        return response;
    }
    
    /**
     * 
     * @param sectionId
     * @return
     */
    @RequestMapping(path = "/get-section-details", method = RequestMethod.GET)
    public RestResponse<Section> getSectionDetails(@RequestParam(name = "section-id") final int sectionId)
    {
        LOG.info("SectionController.getSectionDetails method called for sectionId {} and count {}", sectionId);

        RestResponse<Section> response = new RestResponse<>();

        try
        {
            final Section section = this.service.getSectionDetails(sectionId);
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.SECTION);
            response.setResponse(section);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while fetching the section details for section with id {}", sectionId, e);
            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }

        return response;
    }

    /**
     * 
     * @param sectionId
     * @return
     */
    @RequestMapping(path = "/get-all-sections", method = RequestMethod.GET)
    public RestResponse<List<Section>> getAllSections()
    {
        LOG.info("SectionController.getAllSections method called");

        RestResponse<List<Section>> response = new RestResponse<>();

        try
        {
            final List<Section> sections = this.service.getAllSectionss();
            response.setResponseCode(200);
            response.setResponseMessage("Success");
            response.setType(ResponseType.SECTION);
            response.setResponse(sections);
        }
        catch (Exception e)
        {
            LOG.error("An error occurred while fetching the sections.", e);
            response.setResponseCode(400);
            response.setResponseMessage("Failure: " + e.getMessage());
            response.setType(ResponseType.STRING);
            response.setResponse(null);
        }

        return response;
    }
}