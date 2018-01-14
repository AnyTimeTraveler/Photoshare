package org.simonscode.photoshare.objects;

import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;

@Entity
@Table(name = "tagtype")
public class TagType {
    private Integer id;
    private String Name;

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

    @Column(name = "name", unique = true, nullable = false)
    @GraphQLQuery(name = "name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
