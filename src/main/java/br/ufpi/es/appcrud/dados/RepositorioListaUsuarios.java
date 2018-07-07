package br.ufpi.es.appcrud.dados;

import java.util.LinkedList;
import java.util.List;

import br.ufpi.es.appcrud.modelo.Usuario;

public class RepositorioListaUsuarios implements IRepositorioUsuarios {
	List<Usuario> listaUsuarios = new LinkedList<Usuario>();
	
	/**
	 * Dado um usuário este é inserido na lista de usuários
	 */
	public void inserir(Usuario u) {
		listaUsuarios.add(u);
	}

	/**
	 * Retorna a lista de usuários
	 */
	public List<Usuario> listar() {
		return listaUsuarios;
	}

	public Usuario buscar(String login, String senha) {		
		Usuario usuario=null;
		//percorre toda a lista e checa se o usuário existe com o mesmo login e senha
		for(Usuario u:listaUsuarios){
			if (u.getLogin().equals(login) && u.getSenha().equals(senha)){
				usuario = u;
				return usuario;
			}
		}
		return usuario;
	}
	
	public Usuario buscarPorEmail(String email, String senha) {		
		Usuario usuario=null;
		//percorre toda a lista e checa se o usuário existe com o mesmo login e senha
		for(Usuario u:listaUsuarios){
			if (u.getEmail().equals(email) && u.getSenha().equals(senha)){
				usuario = u;
				return usuario;
			}
		}
		return usuario;
	}
	
	/**
	 * Faz a busca de usuário de acordo com o tipo selecionado
	 * @param conteudo dado do usuário
	 * @param tipo tipo da busca pode ser nome, email, ou login
	 * @return lista contendo o resultado da busca
	 */
	public List<Usuario> buscarPorConteudoETipo(String conteudo, String tipo){
		List<Usuario> lista = new LinkedList<Usuario>();
		switch (tipo) {
		case "nome":
			for (Usuario u:listaUsuarios) {
				if (u.getNome().equals(conteudo)) {
					lista.add(u);
				}
			}
			break;
		case "email":
			for(Usuario u:listaUsuarios) {
				if (u.getEmail().equals(conteudo)) {
					lista.add(u);
				}
			}
			break;
		case "login":
			for(Usuario u:listaUsuarios) {
				if(u.getLogin().equals(conteudo)) {
					lista.add(u);
				}
			}
			break;
		default:
			lista = null;
			break;
		}
		return lista;
	}

	public void alterar(Usuario original, Usuario novo) {
		// TODO Auto-generated method stub
	}

	public void remover(Usuario u) {
		// TODO Auto-generated method stub
	}
	
	public void populaUsuarios(){
		Usuario u1 = new Usuario();
        u1.setId(1);
        u1.setNome("Armando Soares Sousa");
        u1.setLogin("armando");
        u1.setSenha("armando");
        u1.setEmail("armando@ufpi.edu.br");
		
        Usuario u2 = new Usuario();
        u2.setId(2);
        u2.setNome("Maria Soares Sousa");
        u2.setLogin("maria");
        u2.setSenha("maria");
        u2.setEmail("maria@ufpi.edu.br");
		
        Usuario u3 = new Usuario();
        u3.setId(3);
        u3.setNome("João Soares Sousa");
        u3.setLogin("joao");
        u3.setSenha("joao");
        u3.setEmail("joao@ufpi.edu.br");
		
        Usuario u4 = new Usuario();
        u4.setId(4);
        u4.setNome("Francisco Soares Sousa");
        u4.setLogin("francisco");
        u4.setSenha("francisco");
        u4.setEmail("francisco@ufpi.edu.br");
		
        Usuario u5 = new Usuario();
        u5.setId(5);
        u5.setNome("Antonio Soares Sousa");
        u5.setLogin("antonio");
        u5.setSenha("antonio");
        u5.setEmail("antonio@ufpi.edu.br");
		
        this.listaUsuarios.add(u1);
		this.listaUsuarios.add(u2);
		this.listaUsuarios.add(u3);
		this.listaUsuarios.add(u4);
		this.listaUsuarios.add(u5);
	}

}
