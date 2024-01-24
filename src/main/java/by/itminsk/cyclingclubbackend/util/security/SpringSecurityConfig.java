package by.itminsk.cyclingclubbackend.util.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter ;
    private final UserDetailsServiceImpl customerUserDetailsService ;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception
    { http
        // ...
		.cors(cors -> cors.configurationSource(request -> {
            var cr = new CorsConfiguration();
            cr.setAllowedOrigins(List.of("http://localhost:4200"));
            cr.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
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
            .requestMatchers("/api/auth/**", "/api/sms/**", "/api/get/**").permitAll()
            .requestMatchers("/api/private/**").hasAuthority("ADMIN")
//            .requestMatchers(HttpMethod.OPTIONS, "/api/**", "/v2/**", "/swagger-ui/**").permitAll()
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers( "/v3/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/resources/static/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    { return authenticationConfiguration.getAuthenticationManager();}

    @Bean
    public PasswordEncoder passwordEncoder()
    { return new BCryptPasswordEncoder(); }

}
