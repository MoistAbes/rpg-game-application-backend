package dev.zymixon.gateway_service.config;

import dev.zymixon.gateway_service.services.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
//
//@Component
//public class JwtGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtGatewayFilterFactory.Config> {
//    private final JwtUtil jwtUtil;
//
//    public JwtGatewayFilterFactory(JwtUtil jwtUtil) {
//        super(Config.class);
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return new JwtGatewayFilter(jwtUtil); // Return the JwtGatewayFilter with the required dependencies
//    }
//
//    // Define a simple config class for the filter
//    public static class Config {
//        // You can add properties here to configure the filter if needed
//    }
//}
