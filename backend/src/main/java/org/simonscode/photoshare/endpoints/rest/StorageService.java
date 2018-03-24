package org.simonscode.photoshare.endpoints.rest;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public interface StorageService {

    Long store(MultipartFile file) throws IOException;

    Resource load(Long fileId) throws IOException;

}
