package com.stolfa.marketsurveys.errorhandling;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
 
@Provider
public class BadRequestException extends Exception implements
                ExceptionMapper<BadRequestException>
{
    private static final long serialVersionUID = 1L;
 
    public BadRequestException() {
        super("Bad Request");
    }
 
    public BadRequestException(String string) {
        super(string);
    }
 
    @Override
    public Response toResponse(BadRequestException exception)
    {
        return Response.status(400).entity(exception.getMessage())
                                    .type("text/plain").build();
    }
}