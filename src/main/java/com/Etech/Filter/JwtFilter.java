//package com.Etech.Filter;
//
//import com.Etech.Util.JwtUtil;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//    private final JwtUtil jwtUtil;
//
//    private final UserDetailsService userDetailsService;
//
//    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            var token = extractTokenFromRequest(request);
//            if (token != null && jwtUtil.validateToken(token)) {
//                SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(token));
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    public String extractTokenFromRequest(HttpServletRequest request) {
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            var token = authorizationHeader.substring(7);
//            return token;
//        }
//        return null;
//    }
//}