package br.com.tremn.crm.model.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.InterestArea;
import br.com.tremn.crm.model.exception.BusinessException;

/**
 * Serviços para Areas de interesse
 * @author Solkam
 * @since 26 JUL 2015
 */
@Stateless
public class InterestAreaService {

	@PersistenceContext
	private EntityManager manager;
	
	@EJB ContactService contactService;
	
	/**
	 * Salva uma area de interesse aplicando RNs
	 * @param ia
	 * @return
	 */
	public InterestArea saveInterestArea(InterestArea area) {
		verifyIfInterestAreaDescriptionAlreayExists(area);
		return manager.merge( area );
	}
	
	
	/**
	 * RN para garantir a unicidade da descrição de areas de interesse
	 * @param area
	 */
	private void verifyIfInterestAreaDescriptionAlreayExists(InterestArea area) {
		InterestArea foundArea = findInterestAreaByDescription(area.getDescription());
		if (foundArea!=null && !foundArea.equals(area)) {
			throw new BusinessException("Já existe área de interesse com esta descrição");
		}
	}
	
	
	
	/**
	 * Remove uma area de interesse aplicando RNs
	 * @param area
	 */
	public void remove(InterestArea area) {
		verifyIfExistsContactForInterestArea(area);
		manager.remove( manager.merge(area) );
	}

	/**
	 * Verifica se algum contato está associado a esta área de interesse
	 * @param area
	 */
	private void verifyIfExistsContactForInterestArea(InterestArea area) {
		List<Contact> contacts = contactService.searchContactByInterestArea(area);
		if (!contacts.isEmpty()) {
			throw new BusinessException("Existem contatos associados a esta área de interesse");
		}
	}


	/**
	 * Busca uma area de interese pela PK
	 * @param id
	 * @return
	 */
	public InterestArea findInterestAreaById(Long id) {
		return manager.find(InterestArea.class, id);
	}
	

	/**
	 * Busca uma area de interesse pelo nome
	 * @param description
	 * @return
	 */
	private InterestArea findInterestAreaByDescription(String description) {
		try {
			return manager.createNamedQuery("findInterestAreaByDescription", InterestArea.class)
					.setParameter("pDescription", description)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	/**
	 * Pesquisa por todas as areas de interesses
	 * @return
	 */
	public List<InterestArea> searchInterestArea(){
		 return	 manager.createNamedQuery("searchInterestArea", InterestArea.class)
					.getResultList();			
	}
	
	/**
	 * Pesquisa pelas areas ativos somente.
	 * @return
	 */
	public List<InterestArea> searchActiveInterestArea() {
		//pesquisa as areas ativos
		List<InterestArea> areas = manager.createNamedQuery("searchActiveInterestArea", InterestArea.class)
				.getResultList();
		
		return areas;
	}
	
	
	
}
