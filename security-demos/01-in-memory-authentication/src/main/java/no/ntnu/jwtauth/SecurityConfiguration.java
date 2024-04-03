package no.ntnu.jwtauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Creates AuthenticationManager - set up authentication type.
 * The @EnableWebSecurity tells that this ia a class for configuring web security
 */
@Configuration
public class SecurityConfiguration {
  /**
   * This method will be called automatically by the framework to find the authentication to use.
   * Here we specify static users and passwords that will be used for in-memory authentication
   *
   * @param http HTTP Security configuration
   * @throws Exception On authentication error
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // All requests must be authenticated. Redirect to login-form, when not authenticated.
    // Note: this works starting from Spring 6.1 (Spring Boot 3.2).

    http
        // Access to all URLs requires any authenticated user (no specific role)
        .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
        // Use standard login-form
        .formLogin(Customizer.withDefaults());

    return http.build();

    // In earlier Spring versions this worked instead:
    //    http
    //        // This enables the access restrictions
    //        .authorizeHttpRequests()
    //        // This says that access to all URLs requires any authenticated user
    //        // (no specific role)
    //        .requestMatchers("/**").authenticated()
    //        // Redirect to an auto-provided login-form if the user is not authenticated
    //        .and().formLogin();
    //
    //    return http.build();
  }

  /**
   * This will be used as the main service for providing users for the security manager.
   *
   * @return A UserDetailsService managing all the allowed users, their passwords and roles
   */
  @Bean
  public UserDetailsService users() {
    UserDetails user = User.builder()
        .username("chuck")
        // password=Nunchucks
        .password("{bcrypt}$2a$12$/NoknpFFPDlzL3kBryJfsur0yeYC2JFqAs7Fd79ypMP6PN/mtSYmC")
        .roles("USER", "ADMIN")
        .build();
    UserDetails admin = User.builder()
        .username("dave")
        // password=user
        .password("{bcrypt}$2a$10$nwbEjYKgcomq2rjUPge2JegqI.y4zEcNqRMPdqwFnd1ytorNCQM/y")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user, admin);
  }

}
