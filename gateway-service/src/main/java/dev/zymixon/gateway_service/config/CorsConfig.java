//package dev.zymixon.gateway_service.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.List;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        // Create an instance of UrlBasedCorsConfigurationSource
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        // Create and configure CorsConfiguration
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of("http://localhost:4200"));  // Your frontend URL
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//        config.setAllowCredentials(true);
//
//        // Register the configuration for all paths
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
//
//    @Bean
//    public CorsFilter corsFilter(CorsConfigurationSource corsConfigurationSource) {
//        return new CorsFilter(corsConfigurationSource);
//    }
//}
