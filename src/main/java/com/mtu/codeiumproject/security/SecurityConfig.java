package com.mtu.codeiumproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/myapi/pets/**", "/myapi/households/**", "/api/pets/**", "/api/households/**", "/myapi/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/myapi/pets/**", "/myapi/households/**","/api/pets/**", "/api/households/**", "/myapi/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/myapi/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/myapi/pets/**", "/myapi/households/**", "/api/pets/**", "/api/households/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/myapi/households/statistics", "/myapi/households/statistics").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/myapi/pets/**", "/myapi/households/**", "/api/pets/**", "/api/households/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic();

        return http.build();

    }
}
