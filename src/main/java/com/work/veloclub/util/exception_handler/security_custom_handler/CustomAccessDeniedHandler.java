package com.work.veloclub.util.exception_handler.security_custom_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.veloclub.util.exception_handler.error.ErrorContent;
import com.work.veloclub.util.exception_handler.error.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8"); // Устанавливаем кодировку
        ErrorContent errorContent = new ErrorContent(null, "У вас нет прав на выполнение этого действия.");
        ErrorResponse errorResponse = new ErrorResponse(errorContent);

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.getWriter().flush();
    }
}
