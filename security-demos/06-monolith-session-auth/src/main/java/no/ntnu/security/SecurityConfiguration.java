package no.ntnu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Creates AuthenticationManager - set up authentication type.
 * The @EnableWebSecurity tells that this ia a class for configuring web security
 */
@Configuration
public class SecurityConfiguration {
  /**
   * A service providing our users from the database.
   */
  @Autowired
  UserDetailsService userDetailsService;

  /**
   * This method will be called automatically by the framework to find the authentication to use.
   * Here we tell that we want to load users from a database
   *
   * @param auth Authentication builder
   * @throws Exception When user detail service is not found
   */
  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  /**
   * This method will be called automatically by the framework to find the authentication to use.
   *
   * @param http HttpSecurity setting builder
   * @throws Exception When security config fails
   */
  @Bean
  public SecurityFilterChain configureAuthorizationFilterChain(HttpSecurity http) throws Exception {
    // Set up the authorization requests, starting from most restrictive at the top,
    // to the least restrictive on the bottom
    http
        // Disable CSRF and CORS checks. Without this it will be hard to make automated tests.
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        // Configure required access level for the specific URL prefixes
        .authorizeHttpRequests((auth) -> auth.requestMatchers("/admin/**").hasRole("ADMIN"))
        .authorizeHttpRequests((auth) ->
            auth.requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
        )
        // Any other URL is permitted
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        .formLogin(Customizer.withDefaults())
        .logout(logout -> logout.logoutSuccessUrl("/"));
    return http.build();
  }

  /**
   * This method is called to decide what encryption to use for password checking.
   *
   * @return The password encryptor
   */
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
