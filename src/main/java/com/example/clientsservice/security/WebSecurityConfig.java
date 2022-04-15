package com.example.clientsservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import static com.example.clientsservice.models.User.Role.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImplement userDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("a").password("p").roles(ADMIN.name()).
//                and().
//                withUser("u").password("p").roles(USER.name());
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    // old
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/registration", "/authorization").permitAll()
//                .antMatchers("/", "/main").hasAnyAuthority(USER.name(), ADMIN.name())
//                .antMatchers("/users").hasAnyAuthority(ADMIN.name())
//                .and()
//                .formLogin().loginPage("/authorization").permitAll()
//                .and()
//                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/authorization");
//    }


    // new
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration", "/authorization",  "/", "/main", "/users").permitAll()
                .antMatchers("/", "/main").hasAnyAuthority(USER.name(), ADMIN.name())
//                .antMatchers("/users").hasAnyAuthority(ADMIN.name())
                .and()
                .formLogin().loginPage("/authorization").permitAll()
                .and()
                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/authorization");



    }




}
