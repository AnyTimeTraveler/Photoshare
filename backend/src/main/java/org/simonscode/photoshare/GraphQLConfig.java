package org.simonscode.photoshare;

import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.DefaultExecutionStrategyProvider;
import graphql.servlet.ExecutionStrategyProvider;
import graphql.servlet.SimpleGraphQLServlet;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.simonscode.photoshare.endpoints.graphql.PhotoEndpoint;
import org.simonscode.photoshare.endpoints.graphql.TagEndpoint;
import org.simonscode.photoshare.endpoints.graphql.UserEndpoint;
import org.simonscode.photoshare.exceptions.GraphQLExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class GraphQLConfig {

    private final UserEndpoint userEndpoint;
    private final PhotoEndpoint photoEndpoint;
    private final TagEndpoint tagEndpoint;

    @Autowired
    public GraphQLConfig(UserEndpoint userEndpoint, PhotoEndpoint photoEndpoint, TagEndpoint tagEndpoint) {
        this.userEndpoint = userEndpoint;
        this.photoEndpoint = photoEndpoint;
        this.tagEndpoint = tagEndpoint;
    }

    @Bean
    ServletRegistrationBean<SimpleGraphQLServlet> graphQLServletRegistrationBean(GraphQLSchema schema, ExecutionStrategyProvider executionStrategy) {
        return new ServletRegistrationBean<>(new SimpleGraphQLServlet.Builder(schema).withExecutionStrategyProvider(executionStrategy).build(), "/graphql");
    }

    @Bean
    GraphQLSchema schema() {
        return new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(userEndpoint, UserEndpoint.class)
                .withOperationsFromSingleton(photoEndpoint, PhotoEndpoint.class)
                .withOperationsFromSingleton(tagEndpoint, TagEndpoint.class)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
    }

    @Bean
    public ExecutionStrategyProvider executionStrategyProvider() {
        return new DefaultExecutionStrategyProvider(new AsyncExecutionStrategy(new GraphQLExceptionHandler()));
    }
}
