package org.learning.videogameshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  // metodo che restituisce un DatabaseUserDetailsService
  @Bean
  public DatabaseUserDetailsService userDetailsService() {
    return new DatabaseUserDetailsService();
  }

  // metodo che restituisce un PasswordEncoder
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  // metodo che restituisce l'AuthenticationProvider
  @Bean
  public DaoAuthenticationProvider authProvider() {
    // creo un provider vuoto
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    // gli passo lo UserDetailsService
    authenticationProvider.setUserDetailsService(userDetailsService());
    // gli passo il PasswordEncoder
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers("/", "/videogames", "/videogames/show/**", "/types", "/store").permitAll()
        .requestMatchers("/store/purchase").hasAnyAuthority("ADMIN", "USER")
        .requestMatchers("/videogames/create", "/videogames/edit/**", "/videogames/delete/**").hasAuthority("ADMIN")
        .requestMatchers("/types/show/**", "/types/edit/**", "/types/delete/**").hasAuthority("ADMIN")
        .requestMatchers("/limits/**").hasAuthority("ADMIN")
        .requestMatchers("/warehouse").hasAuthority("ADMIN")
        .requestMatchers("/stocks/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/videogames/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/types/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/stocks/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/limits/**").hasAuthority("ADMIN")
        .requestMatchers("/", "/**").permitAll()
        .and().formLogin().loginPage("/login").failureUrl("/login-error")
        .and().logout().logoutSuccessUrl("/store")
        .and().exceptionHandling()
        .and().csrf().disable();
    return http.build();
  }
}
