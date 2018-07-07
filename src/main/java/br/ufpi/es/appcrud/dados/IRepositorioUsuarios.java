package br.ufpi.es.appcrud.dados;

import java.util.List;

import br.ufpi.es.appcrud.modelo.Usuario;

public interface IRepositorioUsuarios {
	public void inserir(Usuario u);
	public List<Usuario> listar();
	public Usuario buscar(String login, String senha);
	public void alterar(Usuario original, Usuario novo);
	public void remover(Usuario u);
	public List<Usuario> buscarPorConteudoETipo(String conteudo, String tipo);
	public Usuario buscarPorEmail(String email, String senha);
}
