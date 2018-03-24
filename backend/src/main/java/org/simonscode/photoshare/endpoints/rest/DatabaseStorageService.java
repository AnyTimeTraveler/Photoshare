package org.simonscode.photoshare.endpoints.rest;


import org.simonscode.photoshare.entities.Photo;
import org.simonscode.photoshare.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class DatabaseStorageService implements StorageService {

    private PhotoRepository photoRepository;

    @Autowired
    public DatabaseStorageService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Long store(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        Photo photo = new Photo();
        photo.setFileName(filename);
        photo.setData(file.getBytes());
        photoRepository.save(photo);
        return photo.getId();
    }

    @Override
    public Resource load(Long fileId) throws IOException {
        Optional<Photo> photo = photoRepository.findById(fileId);
        if (!photo.isPresent()) throw new FileNotFoundException();
        return new ByteArrayResource(photo.get().getData());
    }
}
