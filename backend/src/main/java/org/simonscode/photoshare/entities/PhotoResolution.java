package org.simonscode.photoshare.entities;

import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class PhotoResolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Setter(AccessLevel.PRIVATE)
    @GraphQLIgnore
    private Long id;

    @GraphQLQuery(name = "width")
    private int width;

    @GraphQLQuery(name = "height")
    private int height;

    @GraphQLQuery(name = "url")
    public String getURL() {
        return "/api/files/" + getId();
    }

    @GraphQLIgnore
    private byte[] data;

}
