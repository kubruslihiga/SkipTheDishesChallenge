package br.challenge.skipthedishes.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private SimpleAuthenticationProvider demoAuthenticationProvider;
    
    @Autowired
    private TokenAuthenticationFilter tokenAuthFilter;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {        
        http.csrf().disable().addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class).
        authorizeRequests().anyRequest().permitAll();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {        
        auth.authenticationProvider(demoAuthenticationProvider);        
    }    
    
}
