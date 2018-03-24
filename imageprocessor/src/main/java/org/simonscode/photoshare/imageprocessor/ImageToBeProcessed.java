package org.simonscode.photoshare.imageprocessor;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "imageprocessor", name = "imagetobeprocessed")
@Data
public class ImageToBeProcessed implements Serializable {
    private final byte[] data;
    private Long id;
}
