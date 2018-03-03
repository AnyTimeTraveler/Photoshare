package org.simonscode.photoshare.endpoints.upload;


import org.simonscode.photoshare.entities.Photo;
import org.simonscode.photoshare.entities.Tag;
import org.simonscode.photoshare.entities.TagType;
import org.simonscode.photoshare.repositories.PhotoRepository;
import org.simonscode.photoshare.repositories.TagRepository;
import org.simonscode.photoshare.repositories.TagTypeRepository;
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
    private TagRepository tagRepository;
    private TagTypeRepository tagTypeRepository;

    @Autowired
    public DatabaseStorageService(PhotoRepository photoRepository, TagRepository tagRepository, TagTypeRepository tagTypeRepository) {
        this.photoRepository = photoRepository;
        this.tagRepository = tagRepository;
        this.tagTypeRepository = tagTypeRepository;
    }

    @Override
    public long store(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        Photo photo = new Photo();
        photo.setData(file.getBytes());
        Tag nameTag = new Tag();
        TagType nameTagType = new TagType();
        nameTagType.setName(filename);
        nameTag.setType(nameTagType);
        photo.getTags().add(nameTag);
        photoRepository.save(photo);
        tagRepository.save(nameTag);
        tagTypeRepository.save(nameTagType);
        return photo.getId();
    }

    @Override
    public Resource load(long fileId) throws IOException {
        Optional<Photo> photo = photoRepository.findById(fileId);
        if (!photo.isPresent()) throw new FileNotFoundException();
        return new ByteArrayResource(photo.get().getData());
    }
}
