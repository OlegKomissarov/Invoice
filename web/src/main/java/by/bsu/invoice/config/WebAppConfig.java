package by.bsu.invoice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"by.bsu.invoice"})
@EntityScan(basePackages = "by.bsu.invoice.entity")
@EnableJpaRepositories(basePackages = "by.bsu.invoice.repository")
public class WebAppConfig extends SpringBootServletInitializer implements WebMvcConfigurer {

    public static void main(final String[] args) {
        SpringApplication.run(WebAppConfig.class, args);
    }
}
