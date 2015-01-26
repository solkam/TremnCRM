package br.com.tremn.crm.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.tremn.crm.model.entity.UserTremn;

/**
 * Serviços de negocio para Usuario
 * @author Solkam
 * @since 21 JAN 2015
 */
@Stateless
public class UserService {
	
	
	@PersistenceContext EntityManager manager;
	
	/**
	 * Salva instancia de usuario
	 * @param user
	 * @return
	 */
	public UserTremn saveUserTremn(UserTremn user) {
		return manager.merge(user);
	}
	
	
	/**
	 * Remove instancia de usuario
	 * @param user
	 */
	public void removeUserTremn(UserTremn user) {
		manager.remove( manager.merge(user) );
	}
	
	
	/**
	 * Busca um usuario pelo email e senha. Usado na autenticação
	 * @param email
	 * @param password
	 * @return
	 */
	public UserTremn findUserTremnByEmailAndPassword(String email, String password) {
		try {
			return manager.createNamedQuery("findUserTremnByEmailAndPassword", UserTremn.class)
					.setParameter("pEmail", email)
					.setParameter("pPassword", password)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	/**
	 * Pesquisar usuario pelos filtros usando criteria
	 * @return
	 */
	public List<UserTremn> searchUserTremnByFilters() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UserTremn> criteria = builder.createQuery(UserTremn.class);
		Root<UserTremn> root = criteria.from(UserTremn.class);
		
		Predicate conjunction = builder.conjunction();
		//1.
		
		
		criteria.where( conjunction );
		return manager.createQuery(criteria).getResultList();
	}

}
