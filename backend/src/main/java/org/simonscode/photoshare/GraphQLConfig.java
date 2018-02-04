package org.simonscode.photoshare;

import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.DefaultExecutionStrategyProvider;
import graphql.servlet.ExecutionStrategyProvider;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.simonscode.photoshare.exceptions.ExceptionHandler;
import org.simonscode.photoshare.graphql.PhotoRepository;
import org.simonscode.photoshare.graphql.TagRepository;
import org.simonscode.photoshare.graphql.UserEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class GraphQLConfig {

    @Autowired
    private UserEndpoint userRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private TagRepository tagRepository;

    @Bean
    GraphQLSchema schema() {
        return new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(userRepository, UserEndpoint.class)
                .withOperationsFromSingleton(photoRepository, PhotoRepository.class)
                .withOperationsFromSingleton(tagRepository, TagRepository.class)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
    }


    @Bean
    public ExecutionStrategyProvider executionStrategyProvider() {
        return new DefaultExecutionStrategyProvider(new AsyncExecutionStrategy(new ExceptionHandler()));
    }


    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName(
                        "forward:/graphiql/index.html");
            }
        };
    }
}
