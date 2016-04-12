/**
 * 
 */
package net.stupidiot.iothingers.dao.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Rahul
 *
 */
public class UserDaoTest
{
    @Test
    public void testConnection()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    }
    
    private DataSource getDataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("bfxng4hdq8.database.windows.net:1433");
        ds.setUsername("anyonethere@bfxng4hdq8");
        ds.setPassword("Harmony11");
//      ds.setPassword("iothingers");

        return ds;
    }
}