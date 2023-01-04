package com.classlink.auth.service.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);       
    }
}
