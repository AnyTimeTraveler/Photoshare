package org.simonscode.photoshare.queries;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.hibernate.Session;
import org.simonscode.photoshare.HibernateUtil;
import org.simonscode.photoshare.objects.Photo;
import org.simonscode.photoshare.objects.Tag;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PhotoQuery {


    /**
     * Getting first N elements of a mock list of users
     * <p>
     * Invoke with:
     * {firstNPersons(count: 5) {firstName, lastName}}
     */
    @GraphQLQuery(name = "getPhotosOfTag")
    public Set<Photo> getPhotosOfTag(@GraphQLArgument(name = "tag") int tagId) {
        Session session = HibernateUtil.openSession();
        Tag tag = session.get(Tag.class, tagId);
        return tag.getPhotos();
    }
}
