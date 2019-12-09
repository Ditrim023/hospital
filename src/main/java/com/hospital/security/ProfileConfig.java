package com.hospital.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class ProfileConfig extends WebSecurityConfigurerAdapter {


  private UserService userDetailsService;

  public ProfileConfig(UserService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Autowired
  public void registerAuthenticationManager(AuthenticationManagerBuilder managerBuilder) throws Exception {
    managerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(getPasswordEncoder());
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {

    http.
        authorizeRequests()

        .antMatchers("/index").permitAll()
        .antMatchers("/profile", "patient/edit/{id}", "/patient/info/{id}", "/doctor/info/{id}").hasAnyRole("ADMIN", "DOCTOR")
        .antMatchers("/patient/list", "/patient/add", "/activity", "/doctor/add", "/doctor/edit/{id}", "/doctor/list").hasAnyRole("ADMIN")
        .and().exceptionHandling().accessDeniedPage("/403")
        .and().formLogin()
        .loginPage("/login").failureUrl("/")
        .loginProcessingUrl("/j_spring_security_check")
        .usernameParameter("login")
        .passwordParameter("password")
        .defaultSuccessUrl("/profile")
        .and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login");
//        .and().requiresChannel().requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null).requiresSecure();

  }

  @Override
  public void configure(WebSecurity web) {
    web
        .ignoring()
        .antMatchers("/css/**", "/images/**");
  }

  private BCryptPasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
