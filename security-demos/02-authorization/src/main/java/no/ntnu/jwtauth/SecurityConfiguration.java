package no.ntnu.jwtauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
   *
   * @param http HttpSecurity setting builder
   * @throws Exception On authentication error
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
