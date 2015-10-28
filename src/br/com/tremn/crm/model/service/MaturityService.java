package br.com.tremn.crm.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.tremn.crm.model.entity.Maturity;

/**
 * Serviços de negocio para maturidade
 * @author Solkam
 * @since 15 AGO 2015
 */
@Stateless
public class MaturityService {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public Maturity saveMaturity(Maturity m) {
		return manager.merge(m);
	}
	
	
	public void removeMaturity(Maturity m) {
		manager.remove( manager.merge(m) );
	}

	
	public List<Maturity> searchMaturity() {
		return manager.createNamedQuery("searchMaturity", Maturity.class)
				.getResultList();
	}
	
	
	/**
	 * Busca maturidade pela pk
	 * (usado no converter)
	 * @param id
	 * @return
	 */
	public Maturity findMaturityById(Long id) {
		return manager.find(Maturity.class, id);
	}
	
	/**
	 * Busca uma maturidade conforme a idade
	 * @param age
	 * @return
	 */
	public Maturity findMaturityByAge(Integer age) {
		List<Maturity> maturities = manager.createNamedQuery("findMaturityByAge", Maturity.class)
				.setParameter("pAge", age)
				.getResultList();
		
		if (maturities.isEmpty() ) {
			return null; 
		} else {
			return maturities.get(0);
		}
	}
	

}
