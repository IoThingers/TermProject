/**
 * 
 */
package net.stupidiot.iothingers.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import net.stupidiot.iothingers.dao.mapper.SectionRowMapper;
import net.stupidiot.iothingers.model.Section;

/**
 * @author Rahul
 *
 */
@Repository
public class SectionDao extends JdbcTemplateDao
{
    private static final Logger LOG = LoggerFactory.getLogger(SectionDao.class);

    private static final String UPDATE_SECTION_COUNT = "UPDATE \"dbo\".\"SECTION\" SET COUNT = ? WHERE SECTION_ID = ?";
    private static final String GET_ALL_SECTIONS = "SELECT SECTION_ID, CAPACITY, COUNT, NAME, LIBRARY_ID, LEVEL FROM SECTION";
    
    /**
     * @param sectionId
     * @param count
     * @return
     */
    public int updateCount(int sectionId, int count)
    {
        LOG.info("SectionDao.updateCount method called for sectionId {} and count {}", sectionId, count);
        final int numRows = this.getJdbcTemplate().update(UPDATE_SECTION_COUNT, count, sectionId);
        LOG.info("Successfully updated the count for {} rows for sectionId {}", numRows, sectionId);
        
        return numRows;
    }

    /**
     * @param sectionId
     * @return
     */
    public Section getSectionDetails(final int sectionId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     */
    public List<Section> getAllSections()
    {
        LOG.info("SectinDao.getAllSections method called.");
        
        List<Section> sections = this.getJdbcTemplate().query(GET_ALL_SECTIONS, new SectionRowMapper());
        
        LOG.info("No. of sections fetched: {}", sections.size());
        
        return sections;
    }
}