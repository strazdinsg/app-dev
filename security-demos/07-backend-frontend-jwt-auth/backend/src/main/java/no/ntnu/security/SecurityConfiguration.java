package no.ntnu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Creates AuthenticationManager - set up authentication type
 * The @EnableWebSecurity tells that this ia a class for configuring web security
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  /**
   * A service providing our users from the database
   */
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  /**
   * This method will be called automatically by the framework to find out what authentication to use.
   * Here we tell that we want to load users from a database
   *
   * @param auth Authentication builder
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  /**
   * Configure the authorization rules
   *
   * @param http HTTP Security object
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Allow JWT authentication
    http.cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/authenticate").permitAll()
        .antMatchers("/api/signup").permitAll()
        .antMatchers("/api/products").permitAll()
        .anyRequest().authenticated()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Enable our JWT authentication filter
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    // Necessary authorization for each endpoint will be configured by each method, using @PreAuthorize
  }

  /**
   * This is needed since Spring Boot 2.0, see
   * https://stackoverflow.com/questions/52243774/consider-defining-a-bean-of-type-org-springframework-security-authentication-au
   *
   * @return
   * @throws Exception
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * This method is called to decide what encryption to use for password checking
   *
   * @return The password encryptor
   */
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
