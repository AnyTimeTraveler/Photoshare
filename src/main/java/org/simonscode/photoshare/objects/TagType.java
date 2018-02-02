package org.simonscode.photoshare.objects;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tagtype")
@Data
public class TagType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @GraphQLQuery(name = "id")
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    @GraphQLQuery(name = "name")
    private String name;
}
