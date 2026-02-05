package com.synapse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace(); // 🛡️ Esto hará que el error salga SÍ O SÍ en la consola de Docker
        return Response.status(500)
                .entity(new ErrorResponse(exception.getMessage()))
                .build();
    }

    public static record ErrorResponse(String message) {}
}