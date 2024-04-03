package no.ntnu.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * A filter that is applied to all HTTP requests and checks for a valid JWT token in
 * the `Authorization: Bearer ...` header.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  private static final Logger logger = LoggerFactory.getLogger(
      JwtRequestFilter.class.getSimpleName());

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain)
      throws ServletException, IOException {
    String jwtToken = getJwtToken(request);
    String username = jwtToken != null ? getUsernameFrom(jwtToken) : null;

    if (username != null && notAuthenticatedYet()) {
      UserDetails userDetails = getUserDetailsFromDatabase(username);
      if (jwtUtil.validateToken(jwtToken, userDetails)) {
        registerUserAsAuthenticated(request, userDetails);
      }
    }

    filterChain.doFilter(request, response);
  }

  private UserDetails getUserDetailsFromDatabase(String username) {
    UserDetails userDetails = null;
    try {
      userDetails = userDetailsService.loadUserByUsername(username);
    } catch (UsernameNotFoundException e) {
      logger.warn("User " + username + " not found in the database");
    }
    return userDetails;
  }

  private String getJwtToken(HttpServletRequest request) {
    final String authorizationHeader = request.getHeader("Authorization");
    String jwt = null;
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      jwt = stripBearerPrefixFrom(authorizationHeader);
    }
    return jwt;
  }

  /**
   * Strip the "Bearer " prefix from the Header "Authorization: Bearer ...
   *
   * @param authorizationHeaderValue The value of the Authorization HTTP header
   * @return The JWT token following the "Bearer " prefix
   */
  private static String stripBearerPrefixFrom(String authorizationHeaderValue) {
    final int numberOfCharsToStrip = "Bearer ".length();
    return authorizationHeaderValue.substring(numberOfCharsToStrip);
  }

  private String getUsernameFrom(String jwtToken) {
    String username = null;
    try {
      username = jwtUtil.extractUsername(jwtToken);
    } catch (MalformedJwtException e) {
      logger.warn("Malformed JWT: " + e.getMessage());
    } catch (JwtException e) {
      logger.warn("Error in the JWT token: " + e.getMessage());
    }
    return username;
  }

  private static boolean notAuthenticatedYet() {
    return SecurityContextHolder.getContext().getAuthentication() == null;
  }

  private static void registerUserAsAuthenticated(HttpServletRequest request,
                                                  UserDetails userDetails) {
    final UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
    upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(upat);
  }
}
