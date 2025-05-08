package com.finTechPoint.loanManageSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Set allowed origins - use "*" for public APIs or list specific domains

        /***********
        config.setAllowedOriginPatterns(List.of("*")); // or List.of("http://localhost:3000") for dev only
        
        **/

        config.setAllowedOriginPatterns(List.of("http://103.122.251.232:5173")); // or other trusted domains
        //"http://103.122.251.232:5173" this is Tareq mechine frontend APP
        config.setAllowCredentials(true);  // Only works with specific origins

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);  // Allow cookies/auth headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply to all paths

        return new CorsFilter(source);
    }
}
