package br.com.tremn.crm.model.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.Profession;
import br.com.tremn.crm.model.exception.BusinessException;

/**
 * Serviços para Profissões
 * @author Solkam
 * @since 02 AGO 2015
 */
@Stateless
public class ProfessionService {
	
	@PersistenceContext EntityManager manager;
	
	@EJB ContactService contactService;
	
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
	 * RN que verifica se nome da profissao é unico
	 * @param p
	 */
	private void verifyIfProfessionNameAlreadyExist(Profession p) {
		Profession professionFound = findProfessionByName(p.getName());
		if (professionFound!=null && !professionFound.equals(p)) {
			throw new BusinessException("Já existe Profissão com este nome");
		}
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
		List<Contact> contatsFound = contactService.searchContactByProfession(p);
		if (!contatsFound.isEmpty()) {
			throw new BusinessException("Existem Contatos associados a esta Profissão");
		}
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
	 * Busca profissao pelo nome
	 * @param name
	 * @return null se nao encontrar
	 */
	public Profession findProfessionByName(String name) {
		try {
			return manager.createNamedQuery("findProfessionByName", Profession.class)
					.setParameter("pName", name)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
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
