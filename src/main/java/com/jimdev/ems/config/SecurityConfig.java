package com.jimdev.ems.config;


import com.jimdev.ems.service.EmployeeDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final EmployeeDetailService employeeDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
          http
                  .csrf(c->c.disable())
                  .authorizeHttpRequests(request-> request
                          .requestMatchers(HttpMethod.POST, "/api/v1/employees/login","/api/v1/employees").permitAll()
                          .requestMatchers(HttpMethod.GET, "/api/v1/employees/{id}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                            .requestMatchers(HttpMethod.PATCH, "/api/v1/employees/{id}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                          .requestMatchers("api/v1/employees/**").hasAuthority("ROLE_ADMIN")
                          .anyRequest().authenticated());

          return http.build();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}