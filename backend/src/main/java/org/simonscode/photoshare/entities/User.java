package org.simonscode.photoshare.entities;


import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "member")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @GraphQLQuery(name = "id")
    @Setter(AccessLevel.PRIVATE)
    private long id;

    @Column(name = "username", unique = true, nullable = false)
    @GraphQLQuery(name = "username")
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "first_name", nullable = false)
    @GraphQLQuery(name = "firstName")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @GraphQLQuery(name = "lastName")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @GraphQLQuery(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Photo.class)
    @JoinTable(name = "member_photo", joinColumns = {
            @JoinColumn(name = "member_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "photo_id")
    })
    @GraphQLQuery(name = "photos")
    private Set<Photo> photos;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name = "member_tag", joinColumns = {
            @JoinColumn(name = "member_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "tag_id")
    })
    @GraphQLQuery(name = "tags")
    private Set<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Photo.class)
    @GraphQLQuery(name = "profilePicture")
    private Photo profilePicture;

    @Column(name = "last_online", nullable = false)
    @GraphQLQuery(name = "lastOnline")
    private Date lastOnline;
}
