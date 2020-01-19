package com.pd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
 	UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService);
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/user/**").hasRole("Planinar")
				.antMatchers("/admin/**").hasRole("Sekretar")
				.antMatchers("/guest/**").hasAnyRole("Sekretar", "Planinar", "Gost")
				.antMatchers("/").permitAll()
			.and().
			    formLogin()
			    	.loginPage("/login.jsp")
			    	.loginProcessingUrl("/login")
			    	.defaultSuccessUrl("/guest")
			.and()
//			    .exceptionHandling().accessDeniedPage("/pages/login.jsp?errMsg=loginFailed") //access_denied.jsp
//			    .and()
		    	.rememberMe()
		    .and()
		    	.csrf().disable();
	}
	
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize -> {
			authorize.antMatchers("/").permitAll();
			authorize.antMatchers("/index/**").hasAnyRole("Sekretar", "Planinar", "NijeClan");
			authorize.antMatchers("/admin/**").hasRole("Sekretar");
			authorize.antMatchers("/planinar/**").hasRole("Planinar");
		});
		http.formLogin(login -> {
			login.loginPage("/login.jsp"); // /PD
			login.loginProcessingUrl("/login");
			login.defaultSuccessUrl("/index.jsp"); // PREKO KONTROLERA "/PD/Users/asdf"
//			login.failureForwardUrl("/pages/failure.jsp");
		});
		http.logout(logout -> {
			logout.invalidateHttpSession(true);
			logout.logoutSuccessUrl("/login.jsp");
		});
		http.exceptionHandling(exception -> {
			exception.accessDeniedPage("/pages/access_denied.jsp");
		});

	    http.authorizeRequests().
		    antMatchers("/index/**").hasAnyRole("Sekretar", "Planinar", "NijeClan").
		    antMatchers("/admin/**").hasRole("Sekretar").
		    antMatchers("/planinar/**").hasRole("Planinar").
//		    antMatchers("/").permitAll().
		    and().
		    formLogin().
		    loginPage("/login.jsp").
		    loginProcessingUrl("/login").
		    defaultSuccessUrl("/index.jsp").
		    and().
//		    exceptionHandling().accessDeniedPage("/pages/login.jsp?errMsg=loginFailed"). //access_denied.jsp
//		    and().
		    rememberMe().
		    and().
		    csrf().disable(); //<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	}*/
}