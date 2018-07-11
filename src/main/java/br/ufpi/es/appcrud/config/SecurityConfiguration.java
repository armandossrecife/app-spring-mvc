package br.ufpi.es.appcrud.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Classe responsável pela configuração de segurança do Spring Security
 * @author armandosoaressousa
 *
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //(Cross Site Request Forgery -> Falsificação de solicitação entre sites) 
		http.authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/formularioBuscarUsuario").permitAll()
		.antMatchers(HttpMethod.POST, "/buscarUsuario").permitAll()
		.antMatchers("/listarUsuarios").permitAll()
		.antMatchers("/formularioAlterarUsuario").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/alterarUsuario").hasRole("ADMIN")
		.antMatchers("/listarLogsAcesso").hasRole("ADMIN")
		.antMatchers("/formularioInserirUsuario").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/inserirUsuario").hasRole("ADMIN")
		.antMatchers("/detalharUsuario").hasRole("ADMIN")
		.antMatchers("/removerUsuario").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin();
	}
}
