package org.simonscode.photoshare.imageprocessor;

import java.io.Serializable;
import java.util.List;

public class ProcessedImage implements Serializable {
    private Long id;
    private List<String> errors;
}
