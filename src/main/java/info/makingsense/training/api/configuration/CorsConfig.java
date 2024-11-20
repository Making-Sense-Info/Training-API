package info.makingsense.training.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // Allow all origins. Replace "*" with specific domains if needed.
        corsConfiguration.addAllowedMethod("*"); // Allow all HTTP methods.
        corsConfiguration.addAllowedHeader("*"); // Allow all headers.
        corsConfiguration.setAllowCredentials(true); // Allow credentials like cookies.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // Apply to all paths.

        return new CorsFilter(source);
    }
}

