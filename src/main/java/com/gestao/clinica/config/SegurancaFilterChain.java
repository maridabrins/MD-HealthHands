package com.gestao.clinica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SegurancaFilterChain {

    @Autowired
    SegurancaFilter segurancaFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // Rotas públicas (login e cadastro)
                .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .requestMatchers(HttpMethod.POST, "/medico/create").permitAll()
                .requestMatchers(HttpMethod.POST, "/paciente/create").permitAll()
                .requestMatchers(HttpMethod.GET, "paciente/all").permitAll()
                .requestMatchers(HttpMethod.GET, "medico/all").permitAll()
                
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**"
                    ).permitAll()
                
                .requestMatchers(HttpMethod.POST, "/consultas/agendar").hasAnyRole("ADMIN", "PACIENTE")
                .requestMatchers(HttpMethod.GET, "/consultas/all").hasAnyRole("MEDICO", "PACIENTE", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/consultas/buscar").hasAnyRole("MEDICO", "PACIENTE", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/consultas/update/**").hasAnyRole("MEDICO", "PACIENTE", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/consultas/delete/**").hasAnyRole("MEDICO", "PACIENTE", "ADMIN")
                
                .requestMatchers(HttpMethod.GET, "medico/all").hasAnyRole("ADMIN")
                // Protege todas as outras rotas
                .anyRequest().authenticated()
            )
            .addFilterBefore(segurancaFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
