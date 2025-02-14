package dev.zymixon.gateway_service.config;

import dev.zymixon.gateway_service.services.AuthenticationManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private final AuthenticationManager authenticationManager;

    public SecurityContextRepository(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        // This is not needed since JWT is stateless
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {

        System.out.println("SecurityContextRepository -> load() is running");

        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        System.out.println("Request: " + request.getMethod() + " " + request.getURI());
        System.out.println("Request headers: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {


            String token = authHeader.substring(7); // Remove "Bearer " prefix
            System.out.println("Found bearer token: " + token);

            Authentication auth = new UsernamePasswordAuthenticationToken(token, token);

            System.out.println("Authentication: " + auth.getCredentials());

            return authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        }

        return Mono.empty();
    }
}
