package org.simonscode.photoshare.endpoints.rest;


import org.simonscode.photoshare.entities.PhotoResolution;
import org.simonscode.photoshare.repositories.PhotoResolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseStorageService implements StorageService {

    private PhotoResolutionRepository photoResolutionRepository;

    @Autowired
    public DatabaseStorageService(PhotoResolutionRepository photoResolutionRepository) {
        this.photoResolutionRepository = photoResolutionRepository;
    }

    @Override
    public PhotoResolution store(long id, byte[] data) {
        PhotoResolution resolution = new PhotoResolution();
        resolution.setData(data);
        return photoResolutionRepository.save(resolution);
    }

    @Override
    public byte[] getBytes(long id) {
        return photoResolutionRepository.findById(id).map(PhotoResolution::getData).orElse(null);
    }
}
