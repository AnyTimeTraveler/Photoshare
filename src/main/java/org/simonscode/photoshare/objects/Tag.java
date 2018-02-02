package org.simonscode.photoshare.objects;

import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {
    private long id;
    private Set<Photo> photos;
    private Tag parent;
    private TagType type;

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

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Photo.class)
    @JoinTable(name="photo_tag",
            joinColumns={@JoinColumn(name="tag_id")},
            inverseJoinColumns={@JoinColumn(name="photo_id")})
    @GraphQLQuery(name = "photos")
    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @GraphQLQuery(name = "parent")
    public Tag getParent() {
        return parent;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TagType.class)
    @GraphQLQuery(name = "type")
    public TagType getType() {
        return type;
    }

    public void setType(TagType type) {
        this.type = type;
    }
}
