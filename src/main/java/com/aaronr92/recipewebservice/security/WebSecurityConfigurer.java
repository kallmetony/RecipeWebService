package com.aaronr92.recipewebservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigurer {

    private final UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/api/register").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .cors().and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public static PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
