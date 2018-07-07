package br.ufpi.es.appcrud.dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufpi.es.appcrud.modelo.LogAcesso;

@Repository
@Transactional
public class LogAcessoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	/**
	 * Lista de todos os acessos de todos os usuários que já se logaram no sistema
	 * @return lista de logs
	 */
	public List<LogAcesso> getAcessos(){
		String query = "select l from LogAcesso l";
		return manager.createQuery(query, LogAcesso.class).getResultList();
	}
	
	/**
	 * Insere um log
	 * @param log
	 */
	public void inserir(LogAcesso log){
		manager.persist(log);
	}
}