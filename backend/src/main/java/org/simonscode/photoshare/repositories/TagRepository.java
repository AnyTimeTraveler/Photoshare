package org.simonscode.photoshare.repositories;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.simonscode.photoshare.objects.Tag;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class TagRepository {

    private final EntityManager entityManager;

    public TagRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
