package br.ufpi.es.appcrud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.ufpi.es.appcrud.dados.LogAcessoDAO;
import br.ufpi.es.appcrud.dados.UsuarioDAO;

/**
 * Classe responsável pela configuração de segurança do Spring Security
 * @author armandosoaressousa
 *
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //(Cross Site Request Forgery -> Falsificação de solicitação entre sites) 
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/formularioBuscarUsuario").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/buscarUsuario").hasRole("ADMIN")
		.antMatchers("/listarUsuarios").hasRole("ADMIN")
		.antMatchers("/listarLogsAcesso").hasRole("ADMIN")
		.antMatchers("/formularioInserirUsuario").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/inserirUsuario").hasRole("ADMIN")
		.antMatchers("/detalharUsuario").hasRole("ADMIN")
		.antMatchers("/removerUsuario").hasRole("ADMIN")
		.antMatchers("/removerUsuario/*").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login")
			.defaultSuccessUrl("/principal")
		.and().logout()
			.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.permitAll();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDAO)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
	
    // Forma recomendada de ignorar no filtro de segurança as requisições para recursos estáticos
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
	
}
