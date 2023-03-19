package no.ntnu.jwtauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Creates AuthenticationManager - set up authentication type
 * The @EnableWebSecurity tells that this ia a class for configuring web security
 */
@Configuration
public class SecurityConfiguration {
  /**
   * This method will be called automatically by the framework to find out what authentication to use
   *
   * @param http HttpSecurity setting builder
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        // Disable CSRF and CORS checks. Without this it will be hard to make automated tests
        .cors().and().csrf().disable()
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
   * This will be used as the main service for providing users for the security manager
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
