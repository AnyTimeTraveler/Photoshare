package org.simonscode.photoshare.objects;


import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "member")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @GraphQLQuery(name = "id")
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
            @JoinColumn(name = "member")
    }, inverseJoinColumns = {
            @JoinColumn(name = "photo")
    })
    @GraphQLQuery(name = "photos")
    private Set<Photo> photos;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Tag.class)
    @JoinTable(name = "member_tag", joinColumns = {
            @JoinColumn(name = "member")
    }, inverseJoinColumns = {
            @JoinColumn(name = "tag")
    })
    @GraphQLQuery(name = "tags")
    private Set<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Photo.class)
    @GraphQLQuery(name = "profilePicture")
    private Photo profilePicture;
}
