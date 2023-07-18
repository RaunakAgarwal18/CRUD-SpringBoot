package com.luv2code.springboot.thymeleafdemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.provisioning.JdbcUserDetailsManager;
// import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class demoSecurityConfig {

@Bean
public InMemoryUserDetailsManager userDetailsManager(DataSource dataSource) {

        UserDetails john = User.builder()
                        .username("john")
                        .password("{noop}test123")
                        .roles("EMPLOYEE")
                        .build();

        UserDetails mary = User.builder()
                        .username("mary")
                        .password("{noop}test123")
                        .roles("EMPLOYEE", "MANAGER")
                        .build();

        UserDetails susan = User.builder()
                        .username("susan")
                        .password("{noop}test123")
                        .roles("EMPLOYEE", "MANAGER", "ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
}

// @Bean
// public UserDetailsManager userDetailsManager(DataSource dataSource){
//         JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//         userDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id-?");
//         userDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        
//         return userDetailsManager;
// } 
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/employees/showFormForUpdate").hasRole("MANAGER")
                        .requestMatchers("/employees/delete/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                        .formLogin(form -> form.loginPage("/showMyLoginPage") // Need to create a controller for
                                                                                // this request mapping
                                        .loginProcessingUrl("/authenticateTheUser") // This request is handled
                                                                                        // by Spring, No extra
                                                                                        // coding required
                                        .defaultSuccessUrl("/employees/list", true)
                                        .permitAll())
                        .logout(logout -> logout.permitAll())
                        .exceptionHandling(configurer -> configurer
                                        .accessDeniedPage("/access-denied"));

        return http.build();
}
}
