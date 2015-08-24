package br.com.tremn.crm.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.tremn.crm.model.entity.Maturity;

@Stateless
public class MaturityService {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public Maturity saveMaturity(Maturity m) {
		return manager.merge(m);
	}
	
	public List<Maturity> searchMaturity() {
		return manager.createNamedQuery("searchMaturity", Maturity.class)
				.getResultList();
	}
	
	public void removeMaturity(Maturity m) {
		manager.remove( manager.merge(m) );
	}

}
