package org.simonscode.photoshare.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.simonscode.photoshare.objects.Photo;
import org.simonscode.photoshare.objects.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository
@Transactional
public class PhotoRepository {

    @PersistenceContext
    private EntityManager entityManager;

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
