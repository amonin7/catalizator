package com.onlym.catalizator.config;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtUtil jwtUtil;

    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        String authToken = authentication.getCredentials().toString();

        try {
            if (jwtUtil.validateToken(authToken)) {
                String username = jwtUtil.extractUsername(authToken);
                Claims claims = jwtUtil.getClaims(authToken);
                List<String> role = claims.get("role", List.class);
                List<SimpleGrantedAuthority> authorities = role.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authorities
                );
                return Mono.just(authenticationToken);
            } else {
                return Mono.empty();
            }
        } catch (Exception e) {
            System.out.println(e);
            return Mono.empty();
        }
    }
}
