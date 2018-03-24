package org.simonscode.photoshare.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "imageprocessor")
@Data
public class ImageToBeProcessed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private Long id;
    private byte[] data;
}
