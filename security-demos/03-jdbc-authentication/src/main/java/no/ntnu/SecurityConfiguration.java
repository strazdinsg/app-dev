package no.ntnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Creates AuthenticationManager - set up authentication type.
 */
@Configuration
public class SecurityConfiguration {
  /**
   * A database connection which provides our users. We configure the database in application.properties
   */
  @Autowired
  DataSource dataSource;

  /**
   * Configure the authentication - the user access.
   *
   * @param auth builder for the authentication config
   * @throws Exception When something goes wrong with the database config
   */
  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder auth)
      throws Exception {
    // The database will be initialized from the schema.sql and data.sql scripts
    auth.jdbcAuthentication().dataSource(dataSource);
  }

  /**
   * Use BCrypt encoder for passwords.
   *
   * @return A BCrypt encoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * This method will be called automatically by the framework to find out what authentication to use.
   *
   * @param http HttpSecurity setting builder
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain configureAuthorizationFilterChain(HttpSecurity http) throws Exception {
    // Set up the authorization requests, starting from most restrictive at the top, to least restrictive on bottom
    http
        // This enables the access restrictions
        .authorizeHttpRequests()
        // This configures the requested authorization (role)
        .requestMatchers("/admin").hasRole("ADMIN")
        .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
        .requestMatchers("/").permitAll()
        // Redirect to an auto-provided login-form if the user is not authenticated
        .and().formLogin();

    return http.build();
  }

  /**
   * This method is called when Spring Security needs a BCrypt encoder for passwords.
   *
   * @return The password encryptor
   */
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
