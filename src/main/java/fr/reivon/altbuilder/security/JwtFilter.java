package fr.reivon.altbuilder.security;

import fr.reivon.altbuilder.domain.user.Customer;
import fr.reivon.altbuilder.domain.user.Jwt;
import fr.reivon.altbuilder.service.CustomerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

    CustomerService customerService;
    JwtService jwtService;

    public JwtFilter(CustomerService customerService, JwtService jwtService) {
        this.customerService = customerService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = null;
        Jwt databaseJwt = null;
        boolean isTokenExpired = true;

        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            // get the token, we remove the "Bearer " start string
            String token = authorization.substring(7);
            databaseJwt = jwtService.tokenByValue(token);
            isTokenExpired = jwtService.isTokenExpired(token);
            username = jwtService.extractUsername(token);
        }

        // We check the expiration,
        // if the username is the good one in BDD
        // but also if we are not already auth in the context
        if (!isTokenExpired
                && databaseJwt.getCustomer().getEmail().equals(username)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            // We retrieve the customer entity
            Customer customer = customerService.loadUserByUsername(username);
            // We use a authToken by username and password
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continue the filter
        filterChain.doFilter(request, response);
    }
}
