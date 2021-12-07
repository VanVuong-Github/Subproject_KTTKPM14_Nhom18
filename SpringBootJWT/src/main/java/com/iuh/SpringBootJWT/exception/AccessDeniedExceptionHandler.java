package com.iuh.SpringBootJWT.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handle Unauthorized Error: AccessDeniedException - 403
 */
@Component
public class AccessDeniedExceptionHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedExceptionHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", accessDeniedException.getMessage());
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Lượt truy cập bị từ chối. Bạn không có quyền truy cập tài nguyên này!!!");
    }
}
