package org.simonscode.photoshare.endpoints.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import org.simonscode.photoshare.entities.Photo;
import org.simonscode.photoshare.entities.User;
import org.simonscode.photoshare.exceptions.UnauthorizedException;
import org.simonscode.photoshare.repositories.PhotoRepository;
import org.simonscode.photoshare.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class PhotoEndpoint {

    private final PhotoRepository photoRepository;
    private final TagRepository tagRepository;

    @Autowired
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
    public List<Photo> getPhotosOfTag(@GraphQLArgument(name = "tag") Long tagId) {
        return photoRepository.findAllByTagID(tagId);
    }

    @GraphQLQuery(name = "getOwnedPhotos")
    public List<Photo> getOwnedPhotos(@GraphQLRootContext() graphql.servlet.GraphQLContext context) throws UnauthorizedException {
        Optional<HttpServletRequest> httpRequest = context.getRequest();

        if (httpRequest.isPresent()) {
            Object user = httpRequest.get().getSession().getAttribute("user");
            if (user != null) {
                return photoRepository.findAllByOwnerID(((User) user).getId());
            }
        }
        throw new UnauthorizedException();
    }
}
