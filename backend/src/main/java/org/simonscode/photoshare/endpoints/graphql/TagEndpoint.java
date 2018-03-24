package org.simonscode.photoshare.endpoints.graphql;

import io.leangen.graphql.annotations.GraphQLQuery;
import org.simonscode.photoshare.entities.Tag;
import org.simonscode.photoshare.repositories.TagRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class TagEndpoint {

    private final TagRepository tagRepository;

    public TagEndpoint(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

//    @GraphQLMutation(name = "createTag")
//    public Tag createTag(@GraphQLArgument(name = "parentId", defaultValue = "-1") Long parentId) {
//        Tag tag = new Tag();
//        if (parentId != -1) {
//            tagRepository.findById(parentId).ifPresent(tag::setParent);
//        }
//        tagRepository.save(tag);
//        return tag;
//    }

    @GraphQLQuery(name = "listTags")
    public List<Tag> listTags() {
        return tagRepository.findAll();
    }
}
