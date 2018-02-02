package org.simonscode.photoshare.queries;

import io.leangen.graphql.annotations.GraphQLArgument;
import org.hibernate.Session;
import org.simonscode.photoshare.HibernateUtil;
import org.simonscode.photoshare.objects.Photo;
import org.simonscode.photoshare.objects.Tag;

import java.util.Set;

public class PhotoMutation {

    private Session session = HibernateUtil.openSession();

    public Photo upload(
            @GraphQLArgument(name = "id") int id,
            @GraphQLArgument(name = "tags")Set<Tag> tags){
        Photo photo = new Photo();
        photo.setId(id);
        photo.setTags(tags);
        session.saveOrUpdate(photo);
        return photo;
    }
}
