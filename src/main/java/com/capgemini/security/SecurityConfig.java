package com.capgemini.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capgemini.service.CustomerUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomerUserDetailsService customerDetailsService;
	
	  @Autowired
	    private JwtAuthenticationFilter jwtFilter;

	    @Autowired
	    private JwtAuthenticationEntryPoint entryPoint;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
			http		
			.csrf()  //It is used to Prevent non browser client to Use PostMapping. CSRF is a Cross-Site Request Forgery attack
			.disable()
			.cors()
			.disable()
			.authorizeRequests()
			 .antMatchers("/token").permitAll()
			 .antMatchers(HttpMethod.OPTIONS).permitAll()
			.antMatchers("/user/**").hasRole("NORMAL")
			.antMatchers("/admin/**").hasRole("ADMIN") 
			.anyRequest()
			.authenticated()
			.and()
			 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
             .exceptionHandling().authenticationEntryPoint(entryPoint);
		
			
			
			 http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth ) throws Exception{
		auth.inMemoryAuthentication().withUser("admin1").password(this.passwordEncoder().encode("admin")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user1").password(this.passwordEncoder().encode("user")).roles("NORMAL");
	
		auth.userDetailsService(customerDetailsService).passwordEncoder(passwordEncoder());

	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(10);  
	}
	
	@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

