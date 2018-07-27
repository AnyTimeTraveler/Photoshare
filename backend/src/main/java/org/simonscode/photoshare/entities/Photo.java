package org.simonscode.photoshare.entities;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @GraphQLQuery(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name="photo_tag",
            joinColumns={@JoinColumn(name="photo_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")})
    @GraphQLQuery(name = "tags")
    @Setter(AccessLevel.PRIVATE)
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, targetEntity = PhotoResolution.class)
    @Setter(AccessLevel.PRIVATE)
    private Set<PhotoResolution> resolutions = new HashSet<>();

    @GraphQLQuery(name = "filename")
    private String filename;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @GraphQLQuery(name = "Owner")
    private User owner;
}
