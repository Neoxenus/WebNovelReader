package com.neoxenus.webnovelreader.token.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface TokenService {
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
