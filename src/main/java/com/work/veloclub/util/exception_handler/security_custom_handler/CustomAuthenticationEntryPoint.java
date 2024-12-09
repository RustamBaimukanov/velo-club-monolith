package com.work.veloclub.util.exception_handler.security_custom_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.veloclub.util.exception_handler.error.ErrorContent;
import com.work.veloclub.util.exception_handler.error.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8"); // Устанавливаем кодировку

        // Создаём ErrorContent с нужным сообщением
        ErrorContent errorContent = new ErrorContent(null, "Необходимо войти в систему.");
        ErrorResponse errorResponse = new ErrorResponse(errorContent);

        // Преобразуем ErrorResponse в JSON и записываем в response
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.getWriter().flush();    }
}
