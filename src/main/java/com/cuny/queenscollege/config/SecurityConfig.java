
package com.cuny.queenscollege.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cuny.queenscollege.constants.UserRoles;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		         //any user
		        .antMatchers("/patient/register", "/patient/save", "/user/showForgot", "/user/genNewPwd").permitAll()
		        .antMatchers("/user/login","/login").permitAll()
		        //admin only
				.antMatchers("/doctor/**").hasAuthority(UserRoles.ADMIN.name())
				.antMatchers("/spec/**").hasAuthority(UserRoles.ADMIN.name())
				.antMatchers("/doc/**").hasAuthority(UserRoles.ADMIN.name())
				.antMatchers("/appointment/register","/appointment/save","/appointment/all").hasAuthority(UserRoles.ADMIN.name())
				.antMatchers("/slots/all","/slots/accept","/slots/reject","/slots/dashboard").hasAuthority(UserRoles.ADMIN.name())
				
				//patient login
				.antMatchers("/appointment/view", "/appointment/viewSlot").hasAuthority(UserRoles.PATIENT.name())
				.antMatchers("/slots/book","/slots/cancel").hasAuthority(UserRoles.PATIENT.name())
				
				.anyRequest().authenticated()

				.and()
				.formLogin()
				.loginPage("/user/login")   //show login page
				.loginProcessingUrl("/login")  //do login
				.defaultSuccessUrl("/user/setup", true)
				.failureUrl("/user/login?error= true") //login failed

				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //URL for logout
				.logoutSuccessUrl("/user/login?logout= true")        //on logout sucess
				;
	}

}
