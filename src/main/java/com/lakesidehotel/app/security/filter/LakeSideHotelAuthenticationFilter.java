package com.lakesidehotel.app.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakesidehotel.app.security.jwt.JwtUtil;
import com.lakesidehotel.app.security.manager.LakeSideHotelAuthenticationManager;
import com.lakesidehotel.app.users.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RequiredArgsConstructor
public class LakeSideHotelAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    ObjectMapper mapper = new ObjectMapper();
    private final LakeSideHotelAuthenticationManager lakeSideHotelAuthenticationManager;
    private final JwtUtil jwt;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user;
        try {
            user = mapper.readValue(request.getReader(), User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String email = user.getEmail();
        String password = user.getPassword();

        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authenticatedToken = lakeSideHotelAuthenticationManager.authenticate(authenticationToken);
        if (authenticatedToken != null){
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authenticatedToken);
            return authenticatedToken;
        }
        throw new BadCredentialsException("Invalid User");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String accessToken = jwt.generateAccessToken(userDetails);
        String generateRefreshToken = jwt.generateRefreshToken(userDetails);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", generateRefreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(), tokens);
    }
}
