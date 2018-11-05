package com.space.management.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter {

    @Configuration
    static class ManagementSecurityAdapter extends WebSecurityConfigurerAdapter {
        @Value("${management.security.ignored}")
        private String[] securityIgnored;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers(securityIgnored).permitAll()
                    .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("USER")
                    .and()
                    .httpBasic();
        }
    }
}

