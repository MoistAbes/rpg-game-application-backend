package dev.zymixon.gateway_service.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zymixon.gateway_service.services.AuthenticationManager;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Component
public class WebSecurityConfig {


    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);


    @PostConstruct
    public void init() {
        System.out.println("WebSecurityConfig Initialized");
    }


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        System.out.println("SecurityWebFilterChain Initialized");

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)  // ✅ New Lambda syntax for disabling CSRF
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)  // ✅ Disable form login
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)  // ✅ Disable basic auth
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(auth -> auth
                        .pathMatchers("/auth/**", "/users/register")
                        .permitAll()
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyExchange().authenticated()
                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(CustomAuthenticationHandlers.authenticationEntryPoint)
                        .accessDeniedHandler(CustomAuthenticationHandlers.accessDeniedHandler)
                )
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new org.springframework.web.cors.CorsConfiguration();
                    config.addAllowedOrigin("http://localhost:4200");  // Your frontend URL
                    config.addAllowedMethod("*");  // Allow all methods (GET, POST, etc.)
                    config.addAllowedHeader("*");  // Allow all headers
                    config.setAllowCredentials(true);  // Allow cookies and credentials
                    return config;
                }))
                .build();
    }

}
