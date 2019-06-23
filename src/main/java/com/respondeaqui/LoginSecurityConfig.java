package com.respondeaqui;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT matricula, senha, true FROM usuario WHERE matricula = CAST( ? AS int )")
		.authoritiesByUsernameQuery(
                "SELECT matricula, 'ROLE_USER' FROM usuario WHERE matricula = CAST( ? AS int )")
		.passwordEncoder(passwordEncoder());
	}
   
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/cadastro", "/login", "/criarformulario", "/selectcampus/{id_cidade}", "/selectcurso/{id_campus}", "/editarformulario/{id}/{titulo}", "/editarformulario", "/editarperfil", "/responderformulario/{id}/{link}", "/meusformularios", "/removerconta", "/css/**", "/js/**", "/webjars/**", "/images/**").permitAll()
				.anyRequest().authenticated().and()
				.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/responderformulario").and()
				.logout()
				.logoutSuccessUrl("/login");
	}

}
