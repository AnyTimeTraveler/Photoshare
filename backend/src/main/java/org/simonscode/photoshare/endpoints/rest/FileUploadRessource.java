package org.simonscode.photoshare.endpoints.rest;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.simonscode.photoshare.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@Path("/files/")
public class FileUploadRessource {

    private final StorageService storageService;

    @Autowired
    public FileUploadRessource(StorageService storageService) {
        this.storageService = storageService;
    }

    @GET
    @Path("/{fileId:\\d+}")
    @Produces("image/*")
    public Response serveFile(@PathParam("fileId") Long fileId,
                              @Context HttpServletRequest httpRequest) throws IOException {

        Object user = httpRequest.getSession().getAttribute("user");
        if (user == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        byte[] file = storageService.load(fileId);
        return Response.ok(new ByteArrayInputStream(file), "image/*").build();
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleFileUpload(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @Context HttpServletRequest httpRequest) throws IOException {
        Object user = httpRequest.getSession().getAttribute("user");
        if (user == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        storageService.store((User) user, uploadedInputStream, fileDetail);
        return Response.ok("{'result': 'You successfully uploaded " + fileDetail.getFileName() + "!'}").build();
    }

    @ExceptionHandler(Exception.class)
    public Response handleStorageFileNotFound(Exception e) {
        e.printStackTrace();
        return Response.status(404).entity("File not found :(").build();
    }

}