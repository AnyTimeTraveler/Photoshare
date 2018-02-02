package org.simonscode.photoshare.objects;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

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
    private long id;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name="photo_tag",
            joinColumns={@JoinColumn(name="photo_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")})
    @GraphQLQuery(name = "tags")
    private Set<Tag> tags;

    @Column(name = "data", nullable = false)
    private Byte[] data;

    @GraphQLQuery(name = "url")
    public String getURL() {
        return "/picture/" + getId();
    }
}
