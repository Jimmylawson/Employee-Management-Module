package com.jimdev.ems.config;


import com.jimdev.ems.filters.JwtAuthFilter;
import com.jimdev.ems.service.EmployeeDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final EmployeeDetailService employeeDetailService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthFilter jwtAuthFilter) throws Exception{
          http
                  .csrf(c->c.disable())
                  .authorizeHttpRequests(request-> request
                          .requestMatchers(HttpMethod.POST, "/api/v1/employees/login","/api/v1/employees").permitAll()
                          .requestMatchers(HttpMethod.GET, "/api/v1/employees/{id}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                            .requestMatchers(HttpMethod.PATCH, "/api/v1/employees/{id}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                          .requestMatchers("api/v1/employees/**").hasAuthority("ROLE_ADMIN")
                          .anyRequest().authenticated())
                  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                  .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


          return http.build();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}