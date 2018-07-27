package org.simonscode.photoshare.endpoints.rest;

import org.simonscode.photoshare.entities.PhotoResolution;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface StorageService {

    PhotoResolution store(long id, byte[] data) throws IOException;

    byte[] getBytes(long id) throws IOException;
}
