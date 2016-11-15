package de.fh_kiel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Application configuration for our spring boot app
 *
 * @author jpr
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class ApplicationConfig {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationConfig.class, args);
    }

    /**
     * Register advice for better readability
     *
     * @return the registered {@link CheckNullAdvice}-Bean
     */
    @Bean
    public CheckNullAdvice checkNullAdvice() {
        return new CheckNullAdvice();
    }
}
