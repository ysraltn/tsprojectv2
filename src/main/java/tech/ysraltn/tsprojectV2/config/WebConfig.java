package tech.ysraltn.tsprojectV2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // tüm endpointler için geçerli
                .allowedOrigins(
                    "http://localhost:3000",     // React development
                    "http://127.0.0.1:3000",     // React development alternative
                    "http://192.168.1.115:3000", // Local network
                    "http://35.226.27.83:8080"   // VM production
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
