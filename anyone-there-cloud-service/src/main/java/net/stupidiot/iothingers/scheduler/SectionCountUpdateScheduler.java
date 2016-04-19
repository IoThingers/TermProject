package net.stupidiot.iothingers.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.stupidiot.iothingers.weave.integrator.SectionCountUpdater;

@Component
public class SectionCountUpdateScheduler
{
    private static final Logger LOG = LoggerFactory.getLogger(SectionCountUpdateScheduler.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private SectionCountUpdater updater;

    /**
     * @return the updater
     */
    public SectionCountUpdater getUpdater()
    {
        return updater;
    }

    /**
     * @param updater
     *            the updater to set
     */
    public void setUpdater(SectionCountUpdater updater)
    {
        this.updater = updater;
    }

    /**
     * 
     */
    @Scheduled(fixedRate = 60000)
    public void update()
    {
        LOG.info("The scheduler triggered at {}", DATE_FORMAT.format(new Date(System.currentTimeMillis())));
        updater.run();
    }
}