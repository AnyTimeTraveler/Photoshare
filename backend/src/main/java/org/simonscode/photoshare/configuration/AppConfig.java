package org.simonscode.photoshare.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan({"org.simonscode.photoshare.configuration", "org.simonscode.photoshare.repositories"})
public class AppConfig {
    @Bean
    public TaskScheduler createtaschScheduler() {
        return new ThreadPoolTaskScheduler();
    }
}
