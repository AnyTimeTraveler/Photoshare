package org.simonscode.photoshare.entities;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tagtype")
@Data
public class TagType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @GraphQLQuery(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    @GraphQLQuery(name = "name")
    private String name;

    @Column(name = "showAsAlbum", nullable = false)
    @GraphQLQuery(name = "showAsAlbum")
    private boolean showAsAlbum = false;
}
