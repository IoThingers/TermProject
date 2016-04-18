/**
 * 
 */
package net.stupidiot.iothingers.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.stupidiot.iothingers.dao.SectionDao;
import net.stupidiot.iothingers.model.Section;

/**
 * @author Rahul
 *
 */
@Service
public class SectionService
{
    private static final Logger LOG = LoggerFactory.getLogger(SectionService.class);

    @Autowired
    private SectionDao dao;

    /**
     * @return the dao
     */
    public SectionDao getDao()
    {
        return dao;
    }

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(SectionDao dao)
    {
        this.dao = dao;
    }

    /**
     * @param sectionId
     * @param count
     * @return
     */
    public boolean updateCount(int sectionId, int count)
    {
        LOG.info("SectionService.updateCount method called with sectionId {} and count {}", sectionId, count);
        return this.dao.updateCount(sectionId, count) == 1;
    }

    /**
     * @param sectionId
     * @return
     */
    public Section getSectionDetails(int sectionId)
    {
        LOG.info("SectionService.updateCount method called with sectionId {}", sectionId);
        return this.dao.getSectionDetails(sectionId);
    }

    /**
     * @return
     */
    public List<Section> getAllSectionss()
    {
        LOG.info("SectionService.updateCount method called.");
        return this.dao.getAllSections();
    }
}
