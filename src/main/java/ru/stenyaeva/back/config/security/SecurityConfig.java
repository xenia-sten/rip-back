package ru.stenyaeva.back.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.stenyaeva.back.service.impl.UserDetailsServiceImpl;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(
                List.of("http://localhost:5173"));
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        corsConfig.setAllowedHeaders(List.of("Content-Type","Content-Length", "Authorization","Set-Cookie"));
        corsConfig.setExposedHeaders(List.of("Content-Length", "Content-Type","Authorization","Set-Cookie"));
        corsConfig.setMaxAge(3600L);
        var urlBasedConfig = new UrlBasedCorsConfigurationSource();
        urlBasedConfig.registerCorsConfiguration("/**", corsConfig);
        return urlBasedConfig;
    }
    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers("/auth/**","/folders").permitAll()
                                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
