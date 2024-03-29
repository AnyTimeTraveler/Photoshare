package org.simonscode.photoshare.entities;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tag_type", discriminatorType = DiscriminatorType.STRING)
@Data
public abstract class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @GraphQLQuery(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @GraphQLQuery(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Photo.class)
    @JoinTable(name="photo_tag",
            joinColumns={@JoinColumn(name="tag_id")},
            inverseJoinColumns={@JoinColumn(name="photo_id")})
    @GraphQLQuery(name = "photos")
    @Setter(AccessLevel.PRIVATE)
    private Set<Photo> photos = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @GraphQLQuery(name = "parent")
    private Tag parent;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinTable(name="member_tag",
            joinColumns={@JoinColumn(name="tag_id")},
            inverseJoinColumns={@JoinColumn(name="member_id")})
    @GraphQLQuery(name = "user")
    @Setter(AccessLevel.PRIVATE)
    private Set<User> user = new HashSet<>();
}
