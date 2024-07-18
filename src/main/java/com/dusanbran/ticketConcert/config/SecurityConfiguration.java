package com.dusanbran.ticketConcert.config;

import com.dusanbran.ticketConcert.domain.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authProvider;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/musicians/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/musicians**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/concerts/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/concerts**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/users**").hasAnyAuthority(Role.ADMIN.getAuthority())
                        .requestMatchers(HttpMethod.GET,"/users/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/users/*/boughtTickets").permitAll()
                                .requestMatchers(HttpMethod.GET,"/concerts/*/ticketTypes**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/concerts/allActive**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/musicians**").hasAnyAuthority(Role.ADMIN.getAuthority())
                        .requestMatchers(HttpMethod.POST,"/concerts**").hasAnyAuthority(Role.ADMIN.getAuthority())
                        .requestMatchers(HttpMethod.POST,"/concerts/*/is/*/purchasing").hasAnyAuthority(Role.USER.getAuthority())
                        .requestMatchers(HttpMethod.POST,"/concerts/*/addTicketType**").hasAnyAuthority(Role.ADMIN.getAuthority())

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
