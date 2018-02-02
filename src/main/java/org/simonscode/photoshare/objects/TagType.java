package org.simonscode.photoshare.objects;

import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;

@Entity
@Table(name = "tagtype")
public class TagType {
    private long id;
    private String Name;

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

    @Column(name = "name", unique = true, nullable = false)
    @GraphQLQuery(name = "name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
