package org.simonscode.photoshare.endpoints.rest;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.simonscode.photoshare.entities.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public interface StorageService {

    Long store(User user, InputStream file, FormDataContentDisposition fileDetail) throws IOException;

    byte[] load(Long fileId) throws IOException;

}
