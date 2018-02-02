package org.simonscode.photoshare.objects;

import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "photo")
public class Photo {
    private long id;
    private Set<Tag> tags;
    private Byte[] data;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @GraphQLQuery(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name="photo_tag",
            joinColumns={@JoinColumn(name="photo_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")})
    @GraphQLQuery(name = "tags")
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Column(name = "data", nullable = false)
    public Byte[] getData() {
        return data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    @GraphQLQuery(name = "url")
    public String url() {
        return "/picture/" + getId();
    }
}
