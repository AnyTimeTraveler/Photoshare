package org.simonscode.photoshare.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.simonscode.photoshare.objects.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @GraphQLMutation(name = "createTag")
    public Tag createTag(@GraphQLArgument(name = "parentId", defaultValue = "-1") int parentId) {
        Tag tag = new Tag();
        if (parentId != -1) {
            tag.setParent(entityManager.find(Tag.class, parentId));
        }
        entityManager.persist(tag);
        return tag;
    }

    @GraphQLQuery(name = "listTags")
    public List<Tag> listTags() {
        return entityManager.createQuery("select from tag", Tag.class).getResultList();
    }
}
