package org.simonscode.photoshare;

import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.DefaultExecutionStrategyProvider;
import graphql.servlet.ExecutionStrategyProvider;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.simonscode.photoshare.exceptions.ExceptionHandler;
import org.simonscode.photoshare.endpoints.graphql.PhotoEndpoint;
import org.simonscode.photoshare.endpoints.graphql.TagEndpoint;
import org.simonscode.photoshare.endpoints.graphql.UserEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GraphQLConfig {

    private final UserEndpoint userRepository;
    private final PhotoEndpoint photoRepository;
    private final TagEndpoint tagRepository;

    @Autowired
    public GraphQLConfig(UserEndpoint userRepository, PhotoEndpoint photoRepository, TagEndpoint tagRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.tagRepository = tagRepository;
    }

    @Bean
    GraphQLSchema schema() {
        return new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(userRepository, UserEndpoint.class)
                .withOperationsFromSingleton(photoRepository, PhotoEndpoint.class)
                .withOperationsFromSingleton(tagRepository, TagEndpoint.class)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
    }

    @Bean
    public ExecutionStrategyProvider executionStrategyProvider() {
        return new DefaultExecutionStrategyProvider(new AsyncExecutionStrategy(new ExceptionHandler()));
    }
}
