package com.sige.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   @Autowired
   private UserDetailsService userDetailsService;

   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
   }

   public void configure(WebSecurity web) throws Exception {
      ((IgnoredRequestConfigurer)((IgnoredRequestConfigurer)((IgnoredRequestConfigurer)((IgnoredRequestConfigurer)((IgnoredRequestConfigurer)web.ignoring()
                        .antMatchers(new String[]{"/css/**"}))
                     .antMatchers(new String[]{"/img/**"}))
                  .antMatchers(new String[]{"/js/**"}))
               .antMatchers(new String[]{"/scss/**"}))
            .antMatchers(new String[]{"/webfonts/**"}))
         .antMatchers(new String[]{"/lib/**"});
   }

   protected void configure(HttpSecurity http) throws Exception {
      ((HttpSecurity)((HttpSecurity)((HttpSecurity)((HttpSecurity)((FormLoginConfigurer)((HttpSecurity)((AuthorizedUrl)http.authorizeRequests().anyRequest())
                           .authenticated()
                           .and())
                        .formLogin()
                        .loginPage("/login")
                        .permitAll())
                     .and())
                  .logout()
                  .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                  .and())
               .exceptionHandling()
               .accessDeniedPage("/403")
               .and())
            .csrf()
            .disable())
         .sessionManagement()
         .maximumSessions(10)
         .expiredUrl("/login");
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
