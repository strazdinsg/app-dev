package no.ntnu;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Creates AuthenticationManager - set up authentication type.
 */
@Configuration
public class SecurityConfiguration {
  /**
   * A database connection which provides our users. We configure the database in
   * application.properties
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
   * This method will be called automatically by the framework to find the authentication to use.
   *
   * @param http HttpSecurity setting builder
   * @throws Exception On authentication error
   */
  @Bean
  public SecurityFilterChain configureAuthorizationFilterChain(HttpSecurity http) throws Exception {
    // For Spring Boot 3.2.4 and newer, use this configuration
    http
        // Disable CSRF and CORS checks. Without this it will be hard to make automated tests.
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        // This configures the requested authorization (role):
        // All routes starting with /admin require ADMIN role
        .authorizeHttpRequests((auth) -> auth.requestMatchers("/admin").hasRole("ADMIN"))
        // All routes starting with /user require USER role
        .authorizeHttpRequests((auth) ->
            auth.requestMatchers("/user").hasAnyRole("ADMIN", "USER"))
        // The default URL / is accessible to everyone
        .authorizeHttpRequests((auth) -> auth.requestMatchers("/").permitAll())
        .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
        // Use standard login-form
        .formLogin(Customizer.withDefaults());
    return http.build();

    // For Spring Boot 3.0.2 (and older?) use the following:
    //    http
    //        // Disable CSRF and CORS checks. Without this it will be hard to make automated tests.
    //        .cors().and().csrf().disable()
    //        // This enables the access restrictions
    //        .authorizeHttpRequests()
    //        // This configures the requested authorization (role)
    //        .requestMatchers("/admin").hasRole("ADMIN")
    //        .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
    //        .requestMatchers("/").permitAll()
    //        // Redirect to an auto-provided login-form if the user is not authenticated
    //        .and().formLogin();
    //
    //    return http.build();
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
