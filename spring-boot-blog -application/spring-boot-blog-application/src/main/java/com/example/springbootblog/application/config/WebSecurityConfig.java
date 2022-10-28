package com.example.springbootblog.application.config;


import com.example.springbootblog.application.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig {
@Bean
public static PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
    private static final String[]WHITELIST={
            "/register",
 "/h2-console/*",
    "/"
    };

@Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
    http
            .authorizeRequests()
            .antMatchers(WHITELIST).permitAll()
            .antMatchers(HttpMethod.GET, "/posts/*")
            .permitAll()
            .anyRequest()
            .authenticated();
    http

            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .and()
            .httpBasic();

    http.csrf().disable();
    http.headers().frameOptions().disable();
    return http.build();
}
}




