package no.ntnu.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Utility class for handling JWT tokens.
 * Code from https://youtu.be/X80nJ5T7YpE
 */
@Component
public class JwtUtil {
  @Value("${jwt_secret_key}")
  private String secretKey;
  /**
   * Key inside JWT token where roles are stored.
   */
  private static final String ROLE_KEY = "roles";

  /**
   * Generate a JWT token for an authenticated user.
   *
   * @param userDetails Object containing user details
   * @return JWT token string
   */
  public String generateToken(UserDetails userDetails) {
    final long timeNow = System.currentTimeMillis();
    final long millisecondsInHour = 60 * 60 * 1000;
    final long timeAfterOneHour = timeNow + millisecondsInHour;

    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .claim(ROLE_KEY, userDetails.getAuthorities())
        .setIssuedAt(new Date(timeNow))
        .setExpiration(new Date(timeAfterOneHour))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  /**
   * Find username from a JWT token.
   *
   * @param token JWT token
   * @return Username
   */
  public String extractUsername(String token) throws JwtException {
    return extractClaim(token, Claims::getSubject);
  }

  /**
   * Check if a token is valid for a given user.
   *
   * @param token       Token to validate
   * @param userDetails Object containing user details
   * @return True if the token matches the current user and is still valid
   */
  public boolean validateToken(String token, UserDetails userDetails) throws JwtException {
    final String username = extractUsername(token);
    return userDetails != null
        && username.equals(userDetails.getUsername())
        && !isTokenExpired(token);
  }


  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }


}
