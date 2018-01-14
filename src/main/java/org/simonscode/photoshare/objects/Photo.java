package org.simonscode.photoshare.objects;

import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "photo")
public class Photo {
    private Integer id;
    private Set<Tag> tags;
    private Byte[] data;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @GraphQLQuery(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "photo_tag", joinColumns = {
            @JoinColumn(name = "photo")
    }, inverseJoinColumns = {
            @JoinColumn(name = "tag")
    })
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
