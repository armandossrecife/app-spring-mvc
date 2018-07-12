package br.ufpi.es.appcrud.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufpi.es.appcrud.dados.UsuarioDAO;
import br.ufpi.es.appcrud.infra.FileSaver;
import br.ufpi.es.appcrud.modelo.Usuario;
import br.ufpi.es.appcrud.validation.UsuarioValidation;

import br.ufpi.es.appcrud.utils.GeradorSenha;

@Controller
public class UsuarioController {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	/**
	 * Contrutor
	 */
	public UsuarioController(){
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new UsuarioValidation());
	}
	
	//recurso1
	/**
	 * Carrega a página Home
	 * @return Home.jsp
	 */
	@RequestMapping(value="/recurso1")
	public String recurso1(){
		return "Home";
	}
	
	//recurso2
	/**
	 * Faz a busca de usuários
	 * @param request HttpServletRequest do usuário 
	 * @param session HttpSession do usuário da aplicação
	 * @param model Model da aplicação
	 * @return jsp do ModelAndView da aplicação
	 * @throws ServletException exceção do tipo Servlet
	 * @throws IOException exceção do tipo IOException
	 */
	@RequestMapping(value="/buscarUsuario", method=RequestMethod.POST)
	protected ModelAndView processarBusca(HttpServletRequest request, HttpSession session, Model model) {
		// recupar os dados passados pelo formulario de busca
		String tipo = request.getParameter("opcaotipo");
		String conteudo = request.getParameter("conteudobusca");

		Usuario usuario = usuarioDAO.buscarPorConteudo(conteudo, tipo);

		if (usuario != null) {
			model.addAttribute("usuario", usuario);
		} else {
			model.addAttribute("mensagem", "Não retornou nenhum resultado!");
		}
		return new ModelAndView("usuarios/TelaResultadoBusca");

	}
	
	//recurso 3 
	/**
	 * Carrega o formulário de busca de usuários
	 * @param session Session do usuário da aplicação
	 * @return página TelaBuscarUsuario.jsp | Home.jsp
	 */
	@RequestMapping(value = "/formularioBuscarUsuario", method = RequestMethod.GET)
	public ModelAndView carregaFormularioBusca(HttpSession session) {
		return (new ModelAndView("usuarios/TelaBuscarUsuario"));
	}
	
	//recurso 4
	/**
	 * Lista os usuários da aplicação
	 * @param session Session do usuário da aplicação
	 * @param model Model da aplicação
	 * @return página TelaListarUsuarios.jsp | Home.jsp
	 * @throws IOException trata a exceção IOException caso aconteça
	 */
	@RequestMapping(value="/listarUsuarios", method=RequestMethod.GET)
	public ModelAndView processarListaUsuarios(HttpSession session, Model model) {
		List<Usuario> lista = usuarioDAO.listar();

		model.addAttribute("usuarios", lista);
		return (new ModelAndView("usuarios/TelaListarUsuarios"));
	}
	
	//recurso 5
	/**
	 * Faz a alteração dos dados de um usuário
	 * @param usuario Usuario da aplicação
	 * @param result BindingResult da aplicação para checar os erros
	 * @param session Session do usuário da aplicação
	 * @param redirect RedirectAttributes
	 * @return página TelaListarUsuarios.jsp | Home.jsp
	 * @throws ServletException trata a exceção ServletException caso aconteça
	 * @throws IOException trata a exceção IOException caso aconteça
	 */
	@RequestMapping(value="/alterarUsuario", method=RequestMethod.POST)
	public ModelAndView processarAlterarUsuario(MultipartFile imagem, @Valid Usuario usuario, BindingResult result,
			HttpSession session, RedirectAttributes redirectAttribute) throws ServletException, IOException {
		if (result.hasErrors()) {
			System.out.println((result.getFieldErrorCount("nome") > 0) ? "nome em branco!" : "campo nome ok!");
			System.out.println((result.getFieldErrorCount("login") > 0) ? "login em branco!" : "campo login ok!");
			System.out.println((result.getFieldErrorCount("email") > 0) ? "email em branco!" : "campo email ok!");
			System.out.println((result.getFieldErrorCount("senha") > 0) ? "senha em branco!" : "campo senha ok!");
			return new ModelAndView("usuarios/TelaAlterarUsuario");
		}

		if (!imagem.isEmpty()) {
			String path = fileSaver.write("arquivos-imagem", imagem);
			usuario.setImagemPath(path);
		}
		String senhaOriginal = usuario.getSenha();
		usuario.setSenha(new GeradorSenha().criptografa(senhaOriginal));
		usuarioDAO.alterar(usuario);
		redirectAttribute.addFlashAttribute("mensagem", "Usuário " + usuario.getId() + " alterado com sucesso!");
		return new ModelAndView("redirect:/listarUsuarios");
	}

	//recurso 6
	/**
	 * Carrega o formulário Alterar Usuário
	 * @param session Session do usuário da aplicação 
	 * @return página TelaAlterarUsuario.jsp | Home.jsp
	 */
	@RequestMapping("/formularioAlterarUsuario")
	public ModelAndView carregaFormularioAlterar(int id, HttpSession session) {
		Usuario usuario = usuarioDAO.buscarPorId(id);
		ModelAndView mav = new ModelAndView("usuarios/TelaAlterarUsuario");
		mav.addObject("usuario", usuario);
		return (mav);
	}

	//recurso 7
	/**
	 * Carrega o formulário Inserir Usuário
	 * @param session Session do usuário da aplicação
	 * @return página TelaInserirUsuario.jsp | Home.jsp
	 */
	@RequestMapping(value="/formularioInserirUsuario", method=RequestMethod.GET)
	public String carregarFormularioInserir(HttpSession session) {
		return "usuarios/TelaInserirUsuario";
	}

	//recurso 8
	/**
	 * Insere usuário
	 * @param usuario Dados do Usuário
	 * @param result BindingResult que checa os erros de entrada da interface
	 * @param session Session do usuário da aplicação
	 * @param redirect RedirectAttributes mensagem de redirect
	 * @return página TelaPrincipal.jsp | Home.jsp
	 * @throws ServletException 
	 * @throws IOException
	 */
	@RequestMapping("/inserirUsuario")
	public ModelAndView processarInserirUsuario(MultipartFile imagem, @Valid Usuario usuario, BindingResult result,
			HttpSession session, RedirectAttributes redirectAttribute) throws ServletException, IOException {

		if (result.hasErrors()) {
			System.out.println((result.getFieldErrorCount("nome") > 0) ? "nome em branco!" : "campo nome ok!");
			System.out.println((result.getFieldErrorCount("login") > 0) ? "login em branco!" : "campo login ok!");
			System.out.println((result.getFieldErrorCount("email") > 0) ? "email em branco!" : "campo email ok!");
			System.out.println((result.getFieldErrorCount("senha") > 0) ? "senha em branco!" : "campo senha ok!");
			return new ModelAndView("usuarios/TelaInserirUsuario");
		}
		if (!imagem.isEmpty()) {
			String path = fileSaver.write("arquivos-imagem", imagem);
			usuario.setImagemPath(path);
		}
		String senhaOriginal = usuario.getSenha();
		usuario.setSenha(new GeradorSenha().criptografa(senhaOriginal));
		usuarioDAO.inserir(usuario);
		redirectAttribute.addFlashAttribute("mensagem", "Usuario inserido com sucesso!");
		return new ModelAndView("redirect:/listarUsuarios");
	}
	
	//recurso 9
	/**
	 * Mostra detalhes do usuário selecionado
	 * @param id do usuário
	 * @param session do usuário logado na aplicação
	 * @return view TelaDetalhesUsuario.jsp | Home.jsp
	 */
	@RequestMapping("/detalharUsuario/{id}")
	public ModelAndView processarDetalhesUsuario(@PathVariable("id") Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("usuarios/TelaDetalhesUsuario");
		Usuario usuario = usuarioDAO.buscarPorId(id);
		mav.addObject("usuario", usuario);
		return mav;
	}

	/**
	 * Remove o usuáro seleconado
	 * @param id do usuário selecionado
	 * @param session do usuário logado na aplicação
	 * @param redirectAttribute mensagem do tipo flash para evitar repetição de post
	 * @return view listaUsuários | Home.jsp
	 */
	@RequestMapping("/removerUsuario/{id}")
	public ModelAndView processarRemoverUsuario(@PathVariable("id") Integer id, HttpSession session,
			RedirectAttributes redirectAttribute) {
		Usuario usuario = usuarioDAO.buscarPorId(id);
		usuarioDAO.remover(usuario);
		redirectAttribute.addFlashAttribute("mensagem", "Usuario " + id + " removido com sucesso!");
		return new ModelAndView("redirect:/listarUsuarios");
	}
	
}