package br.ufpi.es.appcrud.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufpi.es.appcrud.dados.LogAcessoDAO;
import br.ufpi.es.appcrud.dados.UsuarioDAO;
import br.ufpi.es.appcrud.modelo.LogAcesso;
import br.ufpi.es.appcrud.modelo.Usuario;
import br.ufpi.es.appcrud.utils.ManipulaData;

@Controller
public class AcessoController {	
	@Autowired
	private LogAcessoDAO logsDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	/**
	 * Construtor
	 */
	public AcessoController(){
	}
		
	/**
	 * Página principal da aplicação
	 * @param session Session do usuário da aplicação
	 * @return TelaPrincipal.jsp | Home.jsp
	 */
	//recurso 1
	@RequestMapping(value="/")
	public ModelAndView home(HttpSession session){
		return new ModelAndView("Home");
		
	}
	
	//recurso2
	/**
	 * Processa o login do usuário 
	 * @param usuario Dados do usuário
	 * @param session Session do usuário da aplicação
	 * @param model Model da aplicação
	 * @return página TelaPrincipal.jsp | TelaLogin.jsp
	 */
	@RequestMapping(value="/efetuarLogin", method=RequestMethod.POST)
	public ModelAndView processarLogin(Usuario usuario, HttpSession session, Model model){
		String email;
		String senha;
		Usuario usuarioAux;
		LogAcesso log = new LogAcesso();
		Date data; 
		
		email = usuario.getEmail();
		senha = usuario.getSenha();
		usuarioAux = usuarioDAO.buscarPorEmail(email, senha);
		
		data = new Date();
		log.setData(data);
		log.setEmail(email);
		log.setDescricao("Login");
			
		if (usuarioAux != null){
			session.setAttribute("usuarioLogado", email);
			session.setAttribute("objetoUsuario", usuarioAux);
			model.addAttribute("mensagem", "Bem vindo " + email);
			System.out.println("Usuario " + email + " logado com sucesso em "+ new ManipulaData().getDataFormatada() + "!");
			logsDAO.inserir(log);
			return new ModelAndView("TelaPrincipal");
		}else{
			model.addAttribute("mensagem", "Erro: usuario ou senha!");
			return new ModelAndView("TelaLogin");
		}
	}
	
	//recurso3
	/**
	 * Carrega o formulário de login da aplicação
	 * @return página TelaLogin.jsp
	 */
	@RequestMapping(value="/formularioLogin")
	public ModelAndView carregarFormularioLogin(){
		return new ModelAndView("TelaLogin");
	}
	
	//recurso4
	/**
	 * Faz o logout e encerramento da sessão do usuário
	 * @param session Session do usuário
	 * @return página TelaLogin.jsp
	 */
	@RequestMapping(value="/logout")
	public ModelAndView processarLogout(HttpSession session) {
		String nomeUsuario;
		LogAcesso log = new LogAcesso();
		Date data; 
	
		nomeUsuario = session.getAttribute("usuarioLogado").toString();
		
		data = new Date();
		log.setData(data);
		log.setEmail(nomeUsuario);
		log.setDescricao("Logout");
		logsDAO.inserir(log);
		session.invalidate();
		System.out.println("Usario " + nomeUsuario + " deslogado");
		return new ModelAndView("TelaLogin");
	}
	
	//recurso 5
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
}