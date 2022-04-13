package com.leonardo.animepoll.config;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
        http
            .csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
            .and()
            
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        if(Arrays.asList(env.getActiveProfiles()).contains("dev")) {
            //Permite o acesso do Spring remote no profile dev
            http
                .authorizeRequests()
                .antMatchers("/.~~spring-boot!~/**").permitAll()
                .and()
                .csrf().disable();
        }

    } 

}
