package com.work.veloclub.security;

import com.work.veloclub.util.exception_handler.security_custom_handler.CustomAccessDeniedHandler;
import com.work.veloclub.util.exception_handler.security_custom_handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ...
                .cors(cors -> cors.configurationSource(request -> {
                    var cr = new CorsConfiguration();
                    cr.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:3000", "http://localhost:8080", "http://localhost:8082", "http://localhost:80", "http://172.19.10.207:80",
                            "http://172.19.10.207:4200", "http://172.19.10.207:8080", "http://172.19.10.207:8082"
                    ));
                    cr.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    cr.setAllowedHeaders(List.of("*"));
                    return cr;
                }))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                //.requestMatchers("/api/register", "/api/authenticate", "/hello", "/api/signup", "/api/restore-password",
                // "/api/signup-complete",  "/api/verify",  "/api/get/**", "/api/get/qualifications", "/api/get/cities",
                // "/api/get/teams", "/api/get/event","/api/check-tel-restore", "/api/check-tel-signup",
                // "/api/restore/restore-password").permitAll()
                .requestMatchers("/api/auth/**", "/api/sms/**").permitAll()
                .requestMatchers("/api/race/**").authenticated()
                .requestMatchers("/api/user/**").authenticated() // Deprecated
                .requestMatchers("/api/users/**").authenticated()
                .requestMatchers("/api/teams/**").authenticated()
                .requestMatchers("/api/news/**").authenticated()
                .requestMatchers("/api/events/**").authenticated()
                .requestMatchers("/api/survey/**").authenticated()

                .requestMatchers("/api/private/**").hasAuthority("ADMIN")
//                .requestMatchers("/api/private/**").permitAll()

//            .requestMatchers(HttpMethod.OPTIONS, "/api/**", "/v2/**", "/swagger-ui/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/resources/static/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
