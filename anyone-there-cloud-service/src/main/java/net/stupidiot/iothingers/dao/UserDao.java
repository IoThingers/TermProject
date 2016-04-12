/**
 * 
 */
package net.stupidiot.iothingers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import net.stupidiot.iothingers.model.User;

/**
 * @author Rahul
 *
 */
@Component
@ComponentScan
public class UserDao
{
    private final String INSERT_USER = "INSERT INTO USERS VALUES(?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @return the jdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    /**
     * @param jdbcTemplate
     *            the jdbcTemplate to set
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 
     * @param user
     * @return
     */
    public int insertUser(final User user)
    {
        final KeyHolder holder = new GeneratedKeyHolder();

        this.jdbcTemplate.update(new PreparedStatementCreator()
        {
            @Override
            public PreparedStatement createPreparedStatement(final Connection con) throws SQLException
            {
                final PreparedStatement ps = con.prepareStatement(INSERT_USER, new String[] { "USERID" });
                ps.setString(1, user.getName());

                return ps;
            }
        }, holder);

        return holder.getKey().intValue();
    }
}