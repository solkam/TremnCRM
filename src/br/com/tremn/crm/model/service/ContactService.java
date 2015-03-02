package br.com.tremn.crm.model.service;

import static br.com.tremn.crm.model.util.QueryUtil.isNotBlank;

import static br.com.tremn.crm.model.util.QueryUtil.toLikeMatchModeSTART;
import static br.com.tremn.crm.model.util.QueryUtil.toLikeMatchModeANY;

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
		Contact foundContact = findContactByEmailPrincipal(c.getEmailPrincipal() );
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
	 * Busca um contato pela PK
	 * @param contactId
	 * @return
	 */
	public Contact findContactById(long contactId) {
		return manager.find(Contact.class, contactId);
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
			Predicate disjunction = builder.disjunction();
			disjunction = builder.or( disjunction, 
					builder.like(root.<String>get("firstName"), toLikeMatchModeSTART(name))
				);
			disjunction = builder.or( disjunction, 
					builder.like(root.<String>get("lastName"), toLikeMatchModeSTART(name))
				);
			conjuction = builder.and( disjunction );
		}
		//2.email
		if (isNotBlank(email)) {
			Predicate disjunction = builder.disjunction();
			disjunction = builder.or( disjunction, 
					builder.equal(root.<String>get("emailPrincipal"), email)
				);
			disjunction = builder.or( disjunction, 
					builder.equal(root.<String>get("emailAlternative"), email)
				);
			conjuction = builder.and( disjunction );
		}
		//2.email
		if (isNotBlank(city)) {
			conjuction = builder.and( conjuction, 
					builder.equal( root.<Address>get("address").<String>get("addressCity"), city )
				);
		}
		//where e orderBy
		criteria.distinct(true);
		criteria.where(conjuction);
		criteria.orderBy( builder.asc( root.<String>get("firstName")), builder.asc( root.<String>get("lastName")) );
		
		List<Contact> contacts = manager.createQuery(criteria).getResultList();
		return contacts;
	}
	
	/**
	 * Pesquisa contact pelo primeiro nome ou ultimo ou cidade
	 * (usando no autocomplete de contatos)
	 * @param name
	 * @param cityName
	 * @return
	 */
	public List<Contact> searchContactByFistNameOrLastNameOrCity(String name, String cityName) {
		List<Contact> contacts = manager.createNamedQuery("searchContactByFistNameOrLastNameOrCity", Contact.class)
				.setParameter("pName", toLikeMatchModeSTART(name))
				.setParameter("pCityName", toLikeMatchModeSTART(cityName))
				.getResultList();
		return contacts;
	}
	
	

	
	/**
	 * Encontra contato pelo email
	 * (usado na RN para salvar contato)
	 * @param email
	 * @return
	 */
	private Contact findContactByEmailPrincipal(String email) {
		try {
			return manager.createNamedQuery("findContactByEmailPrincipal", Contact.class)
					.setParameter("pEmailPrincipal", email)
					.getSingleResult()
					;
		} catch (NoResultException e) {
			return null;
		}
	}


	
	
	

}
