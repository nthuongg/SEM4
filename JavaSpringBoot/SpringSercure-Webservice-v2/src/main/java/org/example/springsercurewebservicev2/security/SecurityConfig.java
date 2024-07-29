package org.example.springsercurewebservicev2.security;

import org.example.springsercurewebservicev2.repository.UserRepository;
import org.example.springsercurewebservicev2.model.AppUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests

                                .requestMatchers(HttpMethod.GET, "/api/books").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/publishers").permitAll()

                                .requestMatchers(HttpMethod.POST, "/api/books").hasAnyRole("EMPLOYEE","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/books/**").hasAnyRole("EMPLOYEE","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasAnyRole("EMPLOYEE","ADMIN")

                                .requestMatchers(HttpMethod.POST, "/api/publishers").hasAnyRole("EMPLOYEE","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/publishers/**").hasAnyRole("EMPLOYEE","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/publishers/**").hasAnyRole("EMPLOYEE","ADMIN")

                                .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.permitAll())
                .logout(logout -> logout.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            AppUser appUser = userRepository.findByUsername(username);
            if (appUser == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return org.springframework.security.core.userdetails.User.withUsername(appUser.getUsername())
                    .password(appUser.getPassword())
                    .authorities(appUser.getRoles().stream()
                            .map(role -> "ROLE_" + role.getName())
                            .toArray(String[]::new))
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}