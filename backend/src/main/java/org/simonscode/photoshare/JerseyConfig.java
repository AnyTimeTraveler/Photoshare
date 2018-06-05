package org.simonscode.photoshare;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.simonscode.photoshare.endpoints.rest.FileUploadRessource;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(FileUploadRessource.class);
        register(MultiPartFeature.class);
    }
}