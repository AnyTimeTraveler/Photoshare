package org.simonscode.photoshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableAsync
@EnableScheduling
@ComponentScan({"org.simonscode.photoshare", "org.simonscode.photoshare.repositories"})
@SpringBootApplication
public class PhotoshareApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PhotoshareApplication.class, args);
    }

    @Bean("taskScheduler")
    public TaskScheduler createtaschScheduler() {
        return new ThreadPoolTaskScheduler();
    }
}
