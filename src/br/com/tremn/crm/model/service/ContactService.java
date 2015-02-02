package br.com.tremn.crm.model.service;

import static br.com.tremn.crm.model.util.QueryUtil.isNotBlank;
import static br.com.tremn.crm.model.util.QueryUtil.toLikeMatchModeSTART;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.tremn.crm.model.entity.Address;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.UserTremn;
import br.com.tremn.crm.model.exception.BusinessException;

/**
 * Serviços de negocio para Contact
 * @author Solkam
 * @since 01 FEV 2015
 */
@Stateless
public class ContactService {
	
	@PersistenceContext
	private EntityManager manager;
	
	@EJB UserService userService;

	/**
	 * Salva contato aplicando RN
	 * @param c
	 * @return
	 */
	public Contact saveContact(Contact c) {
		verifyEmailIsUnique(c);
		return manager.merge( c );
	}
	
	/**
	 * RN para garantir unicidade do email em contato
	 * @param c
	 */
	private void verifyEmailIsUnique(Contact c) {
		Contact foundContact = findContactByEmail(c.getEmail());
		if (foundContact!=null && !foundContact.equals(c)) {
			throw new BusinessException("Email já usado por outro contato");
		}
	}

	/**
	 * Remove contact aplicando RN
	 * @param c
	 */
	public void removeContact(Contact c) {
		verifyContactAssociations(c);
		manager.remove( manager.merge(c) );
	}
	
	
	/**
	 * RN que verifica se existem associados ao contact
	 * que será removido
	 * @param c
	 */
	private void verifyContactAssociations(Contact c) {
		//1.usuario
		UserTremn user = userService.findUserTremnByContact(c);
		if (user!=null) {
			throw new BusinessException("Impossível remover Contato pois existe Usuário associado");
		}
		
	}

	/**
	 * Refresca contato com todas suas associacoes
	 * @param c
	 * @return
	 */
	public Contact refreshContact(Contact c) {
		return c;
	}
	
	
	/**
	 * Pesquisa contact segundo filtros de pesquisa (usando criteria)
	 * @param name
	 * @param email
	 * @param city
	 * @return
	 */
	public List<Contact> searchContactByFilters(String name, String email, String city) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
		Root<Contact> root = criteria.from(Contact.class);
		
		Predicate conjuction = builder.conjunction();
		//1.name
		if (isNotBlank(name)) {
			conjuction = builder.and( conjuction, 
					builder.like(root.<String>get("name"), toLikeMatchModeSTART(name))
				);
		}
		//2.email
		if (isNotBlank(email)) {
			conjuction = builder.and( conjuction, 
					builder.equal(root.<String>get("email"), email)
				);
		}
		//2.email
		if (isNotBlank(city)) {
			conjuction = builder.and( conjuction, 
					builder.equal( root.<Address>get("address").<String>get("addressCity"), city )
				);
		}
		//where e orderBy
		criteria.where(conjuction);
		criteria.orderBy( builder.asc( root.<String>get("name") ));
		
		return manager.createQuery(criteria).getResultList();
	}
	
	
	
	/**
	 * Encontra contato pelo email
	 * (usado na RN para salvar contato)
	 * @param email
	 * @return
	 */
	private Contact findContactByEmail(String email) {
		try {
			return manager.createNamedQuery("findContactByEmail", Contact.class)
					.setParameter("pEmail", email)
					.getSingleResult()
					;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	

}
