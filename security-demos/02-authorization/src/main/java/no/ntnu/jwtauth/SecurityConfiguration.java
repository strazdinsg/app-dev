package no.ntnu.jwtauth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Creates AuthenticationManager - set up authentication type
 * The @EnableWebSecurity tells that this ia a class for configuring web security
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  /**
   * This method will be called automatically by the framework to find out what authentication to use
   *
   * @param auth Authentication builder
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("chuck")
        .password("$2a$12$/NoknpFFPDlzL3kBryJfsur0yeYC2JFqAs7Fd79ypMP6PN/mtSYmC") // == bcrypt("Nunchucks")
        .roles("USER", "ADMIN")
        .and()
        .withUser("dave")
        .password("$2a$10$nwbEjYKgcomq2rjUPge2JegqI.y4zEcNqRMPdqwFnd1ytorNCQM/y") // == bcrypt("user")
        .roles("USER");
  }

  /**
   * Configure the authorization rules
   *
   * @param http HTTP Security object
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Set up the authorization requests, starting from most restrictive at the top, to least restrictive on bottom
    http.authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user").hasAnyRole("USER", "ADMIN")
        .antMatchers("/").permitAll()
        .and().formLogin();
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
