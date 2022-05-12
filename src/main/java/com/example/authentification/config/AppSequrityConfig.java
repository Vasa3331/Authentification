package com.example.authentification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppSequrityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("password1")).roles()
                .and()
                .withUser("user2").password(passwordEncoder().encode("password2")).roles("CLIENT")
                .and()
                .withUser("user3").password(passwordEncoder().encode("password3")).roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/client").hasRole("CLIENT")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and().logout().logoutSuccessUrl("/")
                .and().formLogin();

    }
}
