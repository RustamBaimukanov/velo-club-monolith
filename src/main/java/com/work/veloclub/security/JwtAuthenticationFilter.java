package com.work.veloclub.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private  final JwtUtilities jwtUtilities ;
    private final UserDetailsServiceImpl customerUserDetailsService ;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException, ExpiredJwtException {

        String token = jwtUtilities.getToken(request);
        if (token != null){
            try {
                jwtUtilities.validateToken(token);
                String phoneNumber = jwtUtilities.extractUsername(token);

                UserDetails userDetails = customerUserDetailsService.loadUserByUsername(phoneNumber);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails ,null , userDetails.getAuthorities());
                    log.info("Пользователь {} вошел в систему", phoneNumber);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }
            catch (ExpiredJwtException e){
                String sb = "{ " +
                        "\"error\": {" +
                        "\"message\": \"Время сессии окончено.\"" +
                        "} " +
                        "} ";
                response.setContentType("application/json; charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(sb);
                return;
            }


        }





        filterChain.doFilter(request,response);
    }

}
