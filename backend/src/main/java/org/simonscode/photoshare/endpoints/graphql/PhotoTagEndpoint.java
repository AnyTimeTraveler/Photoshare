package org.simonscode.photoshare.endpoints.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import org.simonscode.photoshare.repositories.PhotoRepository;
import org.simonscode.photoshare.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PhotoTagEndpoint {

    private final PhotoRepository photoRepository;
    private final TagRepository tagRepository;

    @Autowired
    public PhotoTagEndpoint(PhotoRepository photoRepository, TagRepository tagRepository) {
        this.photoRepository = photoRepository;
        this.tagRepository = tagRepository;
    }

    @GraphQLMutation(name = "addTagToPhoto")
    public void add(@GraphQLArgument(name = "photoId") Long photoId,
                    @GraphQLArgument(name = "tagId") Long tagId) {

    }

    @GraphQLMutation(name = "removeTagToPhoto")
    public void remove(@GraphQLArgument(name = "photoId") Long photoId,
                       @GraphQLArgument(name = "tagId") Long tagId) {

    }
}
