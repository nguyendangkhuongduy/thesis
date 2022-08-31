package com.duy.thesisManagement.thesis.config;

import com.duy.thesisManagement.thesis.jwt.AuthEntryPointJwt;
import com.duy.thesisManagement.thesis.jwt.AuthTokenFilter;
import com.duy.thesisManagement.thesis.jwt.JwtUtils;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		private final UserDetailsService userDetailsService;
		private final AuthEntryPointJwt unauthorizedHandler;
		private final JwtUtils jwtUtils;

		@Bean
		public AuthTokenFilter authenticationJwtTokenFilter() {
				return new AuthTokenFilter(jwtUtils, userDetailsService);
		}

		@Override
		public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
				authenticationManagerBuilder.userDetailsService(userDetailsService)
						.passwordEncoder(passwordEncoder());
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
				return super.authenticationManagerBean();
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
				return new BCryptPasswordEncoder();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
				http.cors().and().csrf().disable()
						.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
						.authorizeRequests().antMatchers("/api/auth/**").permitAll()
						.antMatchers("/api/test/**").permitAll()
						.anyRequest().authenticated();
				http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		}

		@Bean
		public CorsConfigurationSource corsConfiguration() {
				CorsConfiguration corsConfig = this.getCorsConfiguration();
				UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
				source.registerCorsConfiguration("/**", corsConfig);
				return source;
		}

		private CorsConfiguration getCorsConfiguration() {
				CorsConfiguration corsConfig = new CorsConfiguration();
				corsConfig.setAllowCredentials(true);
				corsConfig.setAllowedOrigins(Collections.singletonList("*"));
				corsConfig.setAllowedHeaders(Collections.singletonList("*"));
				corsConfig.setAllowedMethods(Collections.singletonList("*"));
				return corsConfig;
		}
}