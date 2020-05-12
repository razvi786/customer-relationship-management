package com.cts.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	PasswordEncoder encoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Autowired
	ZuulConfig properties;
	
	@Autowired
	AuthenticationEntryPoint entryPoint;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
      http.cors().and().csrf().disable()
      	.authorizeRequests()
      	.antMatchers("cloudfoundryapplication/**").permitAll()
      	.anyRequest().authenticated()
        .and()
        .httpBasic()
        .authenticationEntryPoint(entryPoint);
        
      
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser(properties.getUsername())
		.password(encoder.encode(properties.getPassword()))
		.roles(properties.getRoles());
	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource corsSource=new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfig=new CorsConfiguration();
		corsConfig.setAllowCredentials(true);
		corsConfig.addAllowedOrigin("*");
		corsConfig.addAllowedHeader("*");
		corsConfig.addAllowedMethod("OPTIONS");
		corsConfig.addAllowedMethod("GET");
		corsConfig.addAllowedMethod("POST");
		corsConfig.addAllowedMethod("PUT");
		corsConfig.addAllowedMethod("DELETE");
		corsSource.registerCorsConfiguration("/**", corsConfig);
		return new CorsFilter(corsSource);
	}
	
}