/**
 * 
 */
package net.stupidiot.iothingers.exception;

/**
 * @author Rahul
 *
 */
public class UserExistsException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public UserExistsException()
    {
        super("The user already exists.");
    }
}
