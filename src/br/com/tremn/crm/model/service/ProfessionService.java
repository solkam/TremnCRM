package br.com.tremn.crm.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.tremn.crm.model.entity.Profession;

/**
 * Serviços para Profissões
 * @author Solkam
 * @since 27 JUL 2015
 */
@Stateless
public class ProfessionService {
	
	@PersistenceContext EntityManager manager;
	
	/**
	 * Salva uma profissoa aplicando RN
	 * @param p
	 * @return
	 */
	public Profession saveProfession(Profession p) {
		verifyIfProfessionNameAlreadyExist(p);
		return manager.merge( p );
	}
	
	/**
	 * RN que verify se nome da profissao é unico
	 * @param p
	 */
	private void verifyIfProfessionNameAlreadyExist(Profession p) {
		// TODO implementar RN
	}

	
	/**
	 * Remove uma profissao aplicando RN
	 * @param p
	 */
	public void removeProfession(Profession p) {
		verifyIfExistContactForProfession(p);
		manager.remove( manager.merge(p) );
	}
	
	/**
	 * Verifica se algum contato está associado a esta profissão
	 * @param p
	 */
	private void verifyIfExistContactForProfession(Profession p) {
		// TODO implementar RN
	}

	/**
	 * Busca profissao pela PK
	 * @param id
	 * @return
	 */
	public Profession findProfessionById(Long id) {
		return manager.find(Profession.class, id);
	}
	
	/**
	 * Pesquisar todas as profissoes
	 * @return
	 */
	public List<Profession> searchProfession() {
		return manager.createNamedQuery("searchProfession", Profession.class)
				.getResultList();
	}
	
	/**
	 * Pesquisa professoes ativas (para serem usadas em combo)
	 * @return
	 */
	public List<Profession> searchActiveProfession() {
		//pesquisa as profissoes ativas
		List<Profession> professions = manager.createNamedQuery("searchActiveProfession", Profession.class)
				.getResultList();
		
		return professions;
	}
	

}
