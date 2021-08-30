/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.security;

import com.xchan.ftapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Christian
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    
    
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sign-up", "/", "/images/**", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
//            .rememberMe()
//                .tokenValiditySeconds(2592000)
//                .userDetailsService(this.userService)
//                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/?logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
    
    
}
