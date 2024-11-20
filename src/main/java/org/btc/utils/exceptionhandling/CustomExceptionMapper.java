package org.btc.utils.exceptionhandling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        //GenericException ce = (GenericException) e;

        //if (ce.getErrorCode() != null) {
            //consider different responses based on errorCode.
        //}

        return Response.status(500).build();
    }
}
