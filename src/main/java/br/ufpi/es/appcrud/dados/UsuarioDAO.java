package br.ufpi.es.appcrud.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufpi.es.appcrud.modelo.Usuario;

@Repository
@Transactional
public class UsuarioDAO {
	IRepositorioUsuarios repositorio;
	
	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * Construtor
	 * @param tipo repositorio
	 */
	public UsuarioDAO(IRepositorioUsuarios tipo){
		this.repositorio = tipo;
	}
	
	/**
	 * Construtor default
	 */
	public UsuarioDAO(){		
	}
		
	/**
	 * Insere um novo usuário no repositório ORM
	 * @param u dados do Usuario
	 */
	public void inserir(Usuario u) {
		manager.persist(u);
	}

	/**
	 * Faz a busca de um usuário por e-mail e senha
	 * @param email email do usuário 
	 * @param senha senha do usuário
	 * @return Usuário localizado
	 */
	public Usuario buscarPorEmail(String email, String senha) {
		Usuario usuario = null;
		Query query = null;

		String pesquisa = "select u from Usuario u where u.email = :email and u.senha=:senha";
		query = manager.createQuery(pesquisa);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		
		try{
			usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException nre) {
			System.out.println("Erro: " + nre.getMessage());
		}
			
		return (usuario);
	}	
	
	/**
	 * Faz a busca de usuário por nome
	 * @param nome do usuário
	 * @return usuário
	 */
	public Usuario buscarPorNome(String nome){
		Usuario usuario = null;
		Query query = null;

		String pesquisa = "select u from Usuario u where u.nome=:nome";
		query = manager.createQuery(pesquisa);
		query.setParameter("nome", nome);
		
		try{
			usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException nre) {
			System.out.println("Erro: " + nre.getMessage());
			return (null);
		}
			
		return (usuario);
	}
	
	/**
	 * Faz a busca de usuário por e-mail
	 * @param email do usuário
	 * @return usuário
	 */
	public Usuario buscarPorEmail(String email){
		Usuario usuario = null;
		Query query = null;

		String pesquisa = "select u from Usuario u where u.email = :email";
		query = manager.createQuery(pesquisa);
		query.setParameter("email", email);
		
		try{
			usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException nre) {
			System.out.println("Erro: " + nre.getMessage());
			return(null);
		}
			
		return (usuario);

	}
	
	/**
	 * Faz a busca de usuário por login
	 * @param login do usuário
	 * @return usuário
	 */
	public Usuario buscarPorLogin(String login){
		Usuario usuario = null;
		Query query = null;

		String pesquisa = "select u from Usuario u where u.login=:login";
		query = manager.createQuery(pesquisa);
		query.setParameter("login", login);
		
		try{
			usuario = (Usuario) query.getSingleResult();
		}catch (NoResultException nre) {
			System.out.println("Erro: " + nre.getMessage());
			return(null);
		}
			
		return (usuario);
	}
	
	/**
	 * Lista de usuários cadastrados
	 * @return lista de usuários
	 */
	public List<Usuario> listar(){
		String query = "select u from Usuario u";
		return manager.createQuery(query, Usuario.class).getResultList();
	}
	
	/**
	 * Faz a busca de usuário de acordo com o tipo selecionado
	 * @param conteudo dado do usuário
	 * @param tipo tipo da busca pode ser nome, email, ou login
	 * @return usuario contendo o resultado da busca
	 */
	public Usuario buscarPorConteudo(String conteudo, String tipo){
		Usuario usuario=null;
		switch (tipo) {
		case "nome":
			usuario = this.buscarPorNome(conteudo);
			break;
		case "email":
			usuario = this.buscarPorEmail(conteudo);
			break;
		case "login":
			usuario = this.buscarPorLogin(conteudo);
			break;
		default:
			usuario = null;
			break;
		}
		return usuario;
	}

	/**
	 * Dado um usuário remove o usuário
	 * @param u dados do usuario
	 */
	public void remover(Usuario u){
		/*
		 * It works only on entities which are managed in the current transaction/context. In this case, 
		 * you're retrieving the entity in an earlier transaction, storing it in the HTTP session and then 
		 * attempting to remove it in a different transaction/context. 
		 */
		manager.remove(manager.contains(u) ? u : manager.merge(u));
	}
	
	/**
	 * Dado um usuário original alterar os dados do usuário
	 * @param original dados originais
	 * @param novo novos dados
	 */
	public void alterar(Usuario novo){
		manager.merge(novo);
	}

	/**
	 * Faz a busca de um usuário dado o ID
	 * @param id do usuário
	 * @return usuário 
	 */
	public Usuario buscarPorId(int id) {
		return manager.find(Usuario.class, id);
	}
	
}