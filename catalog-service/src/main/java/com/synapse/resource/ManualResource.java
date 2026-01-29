package com.synapse.resource;

import java.io.IOException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import com.synapse.model.Manual;
import com.synapse.service.ManualService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/manuals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManualResource {

    @Inject
    ManualService manSer;

    @ConfigProperty(name = "upload.directory")
    String uploadDir;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadManual(@RestForm("file") FileUpload file, @RestForm("catalogId") String catalogId){

        try{
            Manual m = manSer.save(file, catalogId);

            return Response.status(Response.Status.CREATED).entity(m).build();
        }catch(IOException e){
            return Response.serverError().entity("Error al subir el archivo").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarManual(@PathParam("id") String id){
        java.nio.file.Path path = manSer.getManual(id);

        if (path == null || !java.nio.file.Files.exists(path)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(path.toFile())
            .header("Content-Disposition", "attachment; filename=\"" + path.getFileName() + "\"")
            .build();
    }

}
