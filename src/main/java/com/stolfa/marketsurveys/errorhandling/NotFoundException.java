package com.stolfa.marketsurveys.errorhandling;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
 
@Provider
public class NotFoundException extends Exception implements
                ExceptionMapper<NotFoundException>
{
    private static final long serialVersionUID = 1L;
 
    public NotFoundException() {
        super("Not found");
    }
 
    public NotFoundException(String string) {
        super(string);
    }
 
    @Override
    public Response toResponse(NotFoundException exception)
    {
        return Response.status(404).entity(exception.getMessage())
                                    .type("text/plain").build();
    }
}