package org.simonscode.photoshare.endpoints.rest;


import com.google.common.io.ByteStreams;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.simonscode.photoshare.entities.Photo;
import org.simonscode.photoshare.entities.User;
import org.simonscode.photoshare.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class DatabaseStorageService implements StorageService {

    private PhotoRepository photoRepository;

    @Autowired
    public DatabaseStorageService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Long store(User user, InputStream inputStream, FormDataContentDisposition fileDetail) throws IOException {
        String filename = StringUtils.cleanPath(fileDetail.getFileName());

        Photo photo = new Photo();
        photo.setOwner(user);
        photo.setFilename(filename);
        photo.setData(ByteStreams.toByteArray(inputStream));
        photoRepository.save(photo);
        return photo.getId();
    }

    @Override
    public byte[] load(Long fileId) throws IOException {
        Optional<Photo> photo = photoRepository.findById(fileId);
        if (!photo.isPresent()) throw new FileNotFoundException();
        return photo.get().getData();
    }
}
