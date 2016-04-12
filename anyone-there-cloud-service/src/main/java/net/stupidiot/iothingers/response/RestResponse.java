/**
 * 
 */
package net.stupidiot.iothingers.response;

/**
 * @author Rahul
 *
 */
public class RestResponse<T>
{
    private int responseCode;
    private String responseMessage;
    private ResponseType type;
    private T response;

    /**
     * @return the responseCode
     */
    public int getResponseCode()
    {
        return responseCode;
    }

    /**
     * @param responseCode
     *            the responseCode to set
     */
    public void setResponseCode(int responseCode)
    {
        this.responseCode = responseCode;
    }

    /**
     * @return the responseMessage
     */
    public String getResponseMessage()
    {
        return responseMessage;
    }

    /**
     * @param responseMessage
     *            the responseMessage to set
     */
    public void setResponseMessage(String responseMessage)
    {
        this.responseMessage = responseMessage;
    }

    /**
     * @return the type
     */
    public ResponseType getType()
    {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(ResponseType type)
    {
        this.type = type;
    }

    /**
     * @return the response
     */
    public T getResponse()
    {
        return response;
    }

    /**
     * @param response
     *            the response to set
     */
    public void setResponse(T response)
    {
        this.response = response;
    }
}
