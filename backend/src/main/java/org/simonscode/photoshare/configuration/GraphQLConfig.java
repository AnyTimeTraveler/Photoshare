package org.simonscode.photoshare.configuration;

import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.DefaultExecutionStrategyProvider;
import graphql.servlet.ExecutionStrategyProvider;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.simonscode.photoshare.exceptions.ExceptionHandler;
import org.simonscode.photoshare.repositories.PhotoRepository;
import org.simonscode.photoshare.repositories.TagRepository;
import org.simonscode.photoshare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManager;

@Component
public class GraphQLConfig {

    private EntityManager entityManager;

    @Autowired
    public GraphQLConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    GraphQLSchema schema() {
        return new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(new PhotoRepository(entityManager))
                .withOperationsFromSingleton(new UserRepository(entityManager))
                .withOperationsFromSingleton(new TagRepository(entityManager))
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
