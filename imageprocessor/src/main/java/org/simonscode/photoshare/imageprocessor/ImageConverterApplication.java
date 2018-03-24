package org.simonscode.photoshare.imageprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class ImageConverterApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ImageConverterApplication.class, args);
    }
}
