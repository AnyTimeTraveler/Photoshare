package org.simonscode.photoshare.repositories;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.simonscode.photoshare.objects.Photo;
import org.simonscode.photoshare.objects.Tag;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.Set;

@Component
public class PhotoRepository {

    private final EntityManager entityManager;

    public PhotoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GraphQLMutation(name = "upload")
    public Photo upload(
            @GraphQLArgument(name = "id") int id,
            @GraphQLArgument(name = "tags") Set<Tag> tags) {
        Photo photo = new Photo();
        photo.setId(id);
        photo.setTags(tags);
        entityManager.persist(photo);
        return photo;
    }

    @GraphQLQuery(name = "getPhotosOfTag")
    public Set<Photo> getPhotosOfTag(@GraphQLArgument(name = "tag") int tagId) {
        Tag tag = entityManager.createQuery("select from tag where id = :id", Tag.class).setParameter("id", tagId).getSingleResult();
        return tag.getPhotos();
    }
}
