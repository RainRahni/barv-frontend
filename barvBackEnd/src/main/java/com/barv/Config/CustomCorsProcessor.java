package com.barv.Config;

import com.google.firebase.database.annotations.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsProcessor;
import org.springframework.web.cors.DefaultCorsProcessor;

import java.io.IOException;

public class CustomCorsProcessor<HttpServletResponse> extends DefaultCorsProcessor implements CorsProcessor {
    private static final String ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK = "Access-Control-Request-Private-Network";
    private static final String ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK = "Access-Control-Allow-Private-Network";

    public boolean processRequest(@Nullable CorsConfiguration config, HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        boolean superResult = super.processRequest(config, request, (jakarta.servlet.http.HttpServletResponse) response);
        if (!superResult) return false;

        ServerHttpRequest serverRequest = (ServerHttpRequest) new ServletServerHttpRequest(request);
        if (serverRequest.getHeaders().containsKey(ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK)) {
            ((jakarta.servlet.http.HttpServletResponse) response).addHeader(ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK, Boolean.toString(true));
        }
        return true;
    }
}
