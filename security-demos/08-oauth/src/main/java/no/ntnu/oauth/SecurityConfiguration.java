package no.ntnu.oauth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures the security for the application.
 */
@Configuration
public class SecurityConfiguration {

  /**
   * Configures the security for the application.
   *
   * @param http The HTTP security configuration.
   * @return The security filter chain.
   * @throws Exception If the configuration fails.
   */
  @Bean
  public SecurityFilterChain configureAccess(HttpSecurity http) throws Exception {
    http
        // Disable CSRF checks to simplify the demo
        .csrf(AbstractHttpConfigurer::disable)
        // Enable access to the root path and error page without authentication
        .authorizeHttpRequests(a -> a.requestMatchers("/", "index.html", "/error").permitAll())
        // Any other request requires authentication
        .authorizeHttpRequests(a -> a.anyRequest().authenticated())
        // Use OAuth2 for authentication
        .oauth2Login(Customizer.withDefaults())
        // Allow logout
        .logout(l -> l.logoutSuccessUrl("/").permitAll());
    return http.build();
  }
}
