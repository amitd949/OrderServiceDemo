package com.example.demo.Auth;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class OrderSecurityConfig {
    private static final String SECRET =
            "my-super-secret-key-my-super-secret-key";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        System.out.println(" inside securityFilterChain  ");
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {})
                );

        return http.build();
    }

  /*  @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        System.out.println(" inside securityFilterChain  ");
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(
                                        jwtAuthenticationConverter()
                                )
                        )
                );

        return http.build();
    }*/


    @Bean
    JwtDecoder jwtDecoder() {
        System.out.println(" inside jwtDecoder  ");
        SecretKey key = new SecretKeySpec(
                SECRET.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        return NimbusJwtDecoder.withSecretKey(key).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        System.out.println(" inside jwtEncoder  ");
        SecretKey key = new SecretKeySpec(
                SECRET.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        return new NimbusJwtEncoder(
                new ImmutableSecret<>(key)
        );
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        System.out.println("Inside convertor ");
        JwtGrantedAuthoritiesConverter authoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName("roles");
        authoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(
                authoritiesConverter
        );

        return converter;
    }

    @PostConstruct
    public void test() {
        System.out.println("===== MY SECURITY CONFIG LOADED =====");
    }


}
