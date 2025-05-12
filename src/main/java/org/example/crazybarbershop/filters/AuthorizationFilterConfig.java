package org.example.crazybarbershop.filters;

import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/profile", "/appointment"})
public class AuthorizationFilterConfig extends AuthorizationFilter {

}
