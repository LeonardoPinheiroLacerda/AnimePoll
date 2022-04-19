package com.leonardo.animepoll.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Service;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
        http
            .csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
            .and()
            
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            
    } 

    @Override
    protected UserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    @Service
    public class AppUserDetailsService implements UserDetailsService{

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return null;
        }

    }

}
