package org.btc.utils.exceptionhandling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<GenericException> {

    @Override
    public Response toResponse(GenericException e) {
        if (e.getErrorCode() != null) {
            return handleErrorCode(e);
        } else {
            return Response.status(500).build();
        }
    }

    private Response handleErrorCode(GenericException e) {
        if (e.getErrorCode() == ErrorCode.unsupportedCurrencyCode) {
            return Response.status(500).entity(e.getErrorCode().toString() + " , " +  e.getMessage()).build();
            //consider different responses based on errorCode with a switch case, a map or similar logic.
        } else {
            return Response.status(500).entity(e.getErrorCode().getCode() + " , " +  e.getMessage()).build();
        }
    }
}
