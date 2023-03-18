package no.ntnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Creates AuthenticationManager - set up authentication type
 * The @EnableWebSecurity tells that this ia a class for configuring web security
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  /**
   * A database connection which provides our users. We configure the database in application.properties
   */
  @Autowired
  DataSource dataSource;

  /**
   * This method will be called automatically by the framework to find out what authentication to use.
   * Here we tell that we want to load users from a database
   *
   * @param auth Authentication builder
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        // Here we specify the queries for selecting the necessary fields from our database
        .usersByUsernameQuery("SELECT username, password, is_enabled FROM users WHERE username = ?")
        .authoritiesByUsernameQuery("SELECT username, role FROM roles WHERE username = ?")
    ;
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
