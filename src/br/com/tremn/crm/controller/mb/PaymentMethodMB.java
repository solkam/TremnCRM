package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.PaymentMethod;
import br.com.tremn.crm.model.service.PaymentMethodService;

/**
 * Controller para formas de pagamento
 * @author Solkam
 * @since 14 OUT 2015
 */
@ManagedBean(name="payMB")
@ViewScoped
public class PaymentMethodMB implements Serializable {
	
	@EJB PaymentMethodService service;
	
	private PaymentMethod paymentMethod;

	private List<PaymentMethod> paymentMethods;
	
	
	@PostConstruct void init() {
		populatePaymentMethods();
		reset();
	}
	
	private void populatePaymentMethods() {
		paymentMethods = service.searchPaymentMethod();
		JSFUtil.addMessageAboutResult(paymentMethods);
	}
	
	
	public void reset() {
		paymentMethod = new PaymentMethod();
	}
	
	public void save() {
		paymentMethod = service.savePaymentMethod(paymentMethod);
		populatePaymentMethods();
		reset();
		JSFUtil.addInfoMessage("Forma de Pagamento salva com sucesso");
	}
	
	public void saveAll() {
		for (PaymentMethod pmVar : paymentMethods) {
			service.savePaymentMethod(pmVar);
		}
		populatePaymentMethods();
		JSFUtil.addInfoMessage("Todos as formas de pagamentos salvas com sucesso");
	}
	
	
	public void remove(PaymentMethod selectedOne) {
		service.removePaymentMethod(selectedOne);
		populatePaymentMethods();
		JSFUtil.addInfoMessage("Forma de pagamento removida");
	}
	
	
	
	
	//acessores...
	private static final long serialVersionUID = 5701352198824440519L;
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}
	
	

}
