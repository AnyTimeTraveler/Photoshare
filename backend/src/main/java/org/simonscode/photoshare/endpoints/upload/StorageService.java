package org.simonscode.photoshare.endpoints.upload;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public interface StorageService {

    long store(MultipartFile file) throws IOException;

    Resource load(long fileId) throws IOException;

}
