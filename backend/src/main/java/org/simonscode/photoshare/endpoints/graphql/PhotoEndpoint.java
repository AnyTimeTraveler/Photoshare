package org.simonscode.photoshare.endpoints.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.simonscode.photoshare.entities.Photo;
import org.simonscode.photoshare.repositories.PhotoRepository;
import org.simonscode.photoshare.repositories.TagRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class PhotoEndpoint {

    private final PhotoRepository photoRepository;
    private final TagRepository tagRepository;

    public PhotoEndpoint(PhotoRepository photoRepository, TagRepository tagRepository) {
        this.photoRepository = photoRepository;
        this.tagRepository = tagRepository;
    }

    @GraphQLMutation(name = "createPhoto")
    public Photo create(@GraphQLArgument(name = "tags") List<Long> tagIds) {
        Photo photo = new Photo();
        photo.setTags(tagRepository.findAllByIdIn(tagIds));
        photoRepository.save(photo);
        return photo;
    }

    @GraphQLQuery(name = "getPhotosOfTag")
    public List<Photo> getPhotosOfTag(@GraphQLArgument(name = "tag") long tagId) {
        return photoRepository.findAllByTagID(tagId);
    }
}
