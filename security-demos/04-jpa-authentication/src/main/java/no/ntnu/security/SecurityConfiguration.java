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
   * @throws Exception when DB configuration fails
   */
  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  /**
   * This method will be called automatically by the framework to find the authentication to use.
   *
   * @param http HttpSecurity setting builder
   * @throws Exception when authentication fails
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
   * This method is called to decide what encryption to use for password checking.
   *
   * @return The password encryptor
   */
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
