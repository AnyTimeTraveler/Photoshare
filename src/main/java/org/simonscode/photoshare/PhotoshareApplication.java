package org.simonscode.photoshare;

import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.DefaultExecutionStrategyProvider;
import graphql.servlet.ExecutionStrategyProvider;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.simonscode.photoshare.exceptions.ExceptionHandler;
import org.simonscode.photoshare.queries.PhotoQuery;
import org.simonscode.photoshare.queries.TagMutation;
import org.simonscode.photoshare.queries.UserMutation;
import org.simonscode.photoshare.queries.UserQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = "org.simonscode")
public class PhotoshareApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PhotoshareApplication.class, args);
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

    @Bean
    GraphQLSchema schema() {
        return new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(new PhotoQuery())
                .withOperationsFromSingleton(new UserQuery())
                .withOperationsFromSingleton(new UserMutation())
                .withOperationsFromSingleton(new TagMutation())
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
    }


    @Bean
    public ExecutionStrategyProvider executionStrategyProvider() {
        return new DefaultExecutionStrategyProvider(new AsyncExecutionStrategy(new ExceptionHandler()));
    }

}
