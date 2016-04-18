package net.stupidiot.iothingers;

/**
 * 
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author Rahul
 */
@EnableScheduling
@SpringBootApplication
public class Application 
{
    public static void main(String[] args) 
    {
        SpringApplication.run(Application.class, args);
    }
}
