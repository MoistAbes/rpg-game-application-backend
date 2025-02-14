package dev.zymixon.gateway_service.config;


import dev.zymixon.gateway_service.services.AuthenticationManager;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Component
public class WebSecurityConfig {


    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

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
                        .pathMatchers("/auth/**")
                        .permitAll()
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyExchange().authenticated()
                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((swe, e) ->
                                Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                        )
                        .accessDeniedHandler((swe, e) ->
                                Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                        )
                )
                .build();
    }

}
