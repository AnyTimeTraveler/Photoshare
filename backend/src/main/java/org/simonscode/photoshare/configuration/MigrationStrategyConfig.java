package org.simonscode.photoshare.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Slf4j
public class MigrationStrategyConfig {

    @Value("${spring.jpa.properties.hibernate.default_schema}")
    private String schema;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FlywayMigrationStrategy migrationStrategy() {
        return flyway -> {
            flyway.setSchemas(schema);
            flyway.setOutOfOrder(false);
            flyway.clean();
            flyway.migrate();
        };
    }
}
