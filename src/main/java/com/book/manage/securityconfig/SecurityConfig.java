package com.book.manage.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.book.manage.repositories.UserRepo;

@Configuration
public class SecurityConfig {

    public SecurityConfig(UserRepo userRepository) {
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/register", "/login", "/css/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(login -> login
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                )
                .logout(logout -> logout
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                );
        return http.build();
    }
}
