package org.simonscode.photoshare.entities;

import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "photo")
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @GraphQLQuery(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name="photo_tag",
            joinColumns={@JoinColumn(name="photo_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")})
    @GraphQLQuery(name = "tags")
    private Set<Tag> tags;

    @Column(name = "data")
    @GraphQLIgnore
    private byte[] data;

    @GraphQLQuery(name = "url")
    public String getURL() {
        return "/files/" + getId();
    }
}
