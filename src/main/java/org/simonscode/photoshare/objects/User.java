package org.simonscode.photoshare.objects;


import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "member")
public class User {
    private long id;
    private String username;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String email;
    private Photo profilePicture;

    private Set<Photo> photos;
    private Set<Tag> tags;

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

    @Column(name = "username", unique = true, nullable = false)
    @GraphQLQuery(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password_hash", nullable = false)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Column(name = "first_name", nullable = false)
    @GraphQLQuery(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    @GraphQLQuery(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", unique = true, nullable = false)
    @GraphQLQuery(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Photo.class)
    @JoinTable(name = "member_photo", joinColumns = {
            @JoinColumn(name = "member")
    }, inverseJoinColumns = {
            @JoinColumn(name = "photo")
    })
    @GraphQLQuery(name = "photos")
    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name = "member_tag", joinColumns = {
            @JoinColumn(name = "member")
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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Photo.class)
    @GraphQLQuery(name = "profilePicture")
    public Photo getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Photo profilePicture) {
        this.profilePicture = profilePicture;
    }
}
