package br.com.tremn.crm.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.tremn.crm.model.entity.PaymentMethod;

/**
 * Serviço para formas de pagamento
 * @author Solkam
 * @since 14 OUT 2015
 */
@Stateless
public class PaymentMethodService {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public PaymentMethod savePaymentMethod(PaymentMethod pm) {
		return manager.merge( pm );
	}
	
	public void removePaymentMethod(PaymentMethod pm) {
		manager.remove( manager.merge(pm) );
	}
	
	
	public PaymentMethod findPaymentMethod(Long id) {
		return manager.find(PaymentMethod.class, id);
	}

	
	public List<PaymentMethod> searchPaymentMethod() {
		return manager.createNamedQuery("searchPaymentMethod", PaymentMethod.class)
				.getResultList();
	}

	
	public List<PaymentMethod> searchPaymentMethodByFlagActive(boolean flagActive) {
		return manager.createNamedQuery("searchPaymentMethodByFlagActive", PaymentMethod.class)
				.setParameter("pFlagActive", flagActive)
				.getResultList();
	}
}
