package br.ufpi.es.appcrud.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufpi.es.appcrud.dados.LogAcessoDAO;
import br.ufpi.es.appcrud.modelo.LogAcesso;
import br.ufpi.es.appcrud.modelo.Usuario;
import br.ufpi.es.appcrud.utils.ManipulaData;

@Controller
public class AcessoController {	
	@Autowired
	private LogAcessoDAO logsDAO;

	/**
	 * Página principal da aplicação
	 * @param session Session do usuário da aplicação
	 * @return TelaPrincipal.jsp | Home.jsp
	 */
	@RequestMapping(value="/")
	public ModelAndView home(HttpSession session){
		return new ModelAndView("Home");
	}
	
	/**
	 * Faz o login do usuário
	 * @param username e-mail do usuário
	 * @param session sessão do usuário
	 * @return TelaLogin.jsp
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		return new ModelAndView("TelaLogin");
	}

	/**
	 * Faz o logout e encerramento da sessão do usuário
	 * @param session Session do usuário
	 * @return página TelaLogin.jsp
	 */
	@RequestMapping(value="/logout")
	public ModelAndView processarLogout(HttpSession session) {
		//TODO corrigir o registro de logout na aplicação
		String loginUsuario;
		LogAcesso log = new LogAcesso();
		Date data; 
	
		loginUsuario = session.getAttribute("emailUsuario").toString();
		data = new Date();
		log.setData(data);
		log.setEmail(loginUsuario);
		log.setDescricao("Logout");
		logsDAO.inserir(log);
		session.invalidate();
		
		System.out.println("Usuario " + loginUsuario + " deslogado" + new ManipulaData().getDataFormatada());
		return new ModelAndView("TelaLogin");
	}
	
	/**
	 * Lista os logs de acessos dos usuários da aplicação
	 * @param session Session do usuário da aplicação
	 * @param model Model da aplicação
	 * @return página TelaListarLogsAcesso.jsp | Home.jsp
	 * @throws IOException trata a exceção IOException caso aconteça
	 */
	@RequestMapping(value="/listarLogsAcesso", method=RequestMethod.GET)
	public ModelAndView processarListaLogs(HttpSession session, Model model) {
		List<LogAcesso> lista = logsDAO.getAcessos();

		model.addAttribute("logs", lista);
		return (new ModelAndView("logs/TelaListarLogsAcesso"));
	}
	
	/**
	 * Dashboard da aplicação
	 * @param session sessão do usuário
	 * @return TelaPrincipal.jsp
	 */
	@RequestMapping(value="/principal")
	public ModelAndView dashBoard(HttpSession session){
		//TODO corrigir o registro de login na aplicação
		Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
		String loginUsuarioCorrente = autenticacao.getName(); 
		Usuario usuario = (Usuario) autenticacao.getPrincipal();
		if (autenticacao.isAuthenticated()){
			session.setAttribute("emailUsuario", loginUsuarioCorrente);
			session.setAttribute("usuario", usuario);
		}
		return new ModelAndView("TelaPrincipal");
	}
	
}