package dev.zymixon.gateway_service.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

public class CustomAuthenticationHandlers {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationHandlers.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static final ServerAuthenticationEntryPoint authenticationEntryPoint = (exchange, e) -> {
        logger.error("🔴 AuthenticationEntryPoint triggered: {}", e.getClass().getSimpleName());

        return buildErrorResponse(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized", "JWT expired or invalid");
    };

    public static final ServerAccessDeniedHandler accessDeniedHandler = (exchange, e) -> {
        logger.error("🔴 AccessDeniedHandler triggered: {}", e.getClass().getSimpleName());

        return buildErrorResponse(exchange, HttpStatus.FORBIDDEN, "Forbidden", "You do not have permission to access this resource");
    };

    private static Mono<Void> buildErrorResponse(ServerWebExchange exchange, HttpStatus status, String error, String message) {
        Map<String, String> errorResponse = Map.of(
                "error", error,
                "message", message
        );

        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(errorResponse);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Error serializing JSON response", ex);
        }

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().setContentLength(bytes.length);

        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
    }
}