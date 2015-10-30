package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Event;
import br.com.tremn.crm.model.entity.PaymentMethod;
import br.com.tremn.crm.model.entity.Product;
import br.com.tremn.crm.model.entity.enumeration.EventStatus;
import br.com.tremn.crm.model.service.PaymentMethodService;
import br.com.tremn.crm.model.service.ProductService;
import br.com.tremn.crm.model.service.ReportService;

/**
 * Controller para relatorio de eventos
 * @author Solkam
 * @since 28 OuT 2015
 */
@ManagedBean(name="rEventMB")
@ViewScoped
public class ReportEventMB implements Serializable {
	
	@EJB ReportService reportService;
	
	@EJB ProductService productService;
	@EJB PaymentMethodService paymentMethodService;
	
	//combos
	private List<Product> comboProducts;
	private List<PaymentMethod> comboPaymentMethods;
	
	//filter
	private List<Integer> filterMonths;
	private Integer filterYear;
	private List<Product> filterProducts;
	private List<EventStatus> filterEventStatusList;
	private List<PaymentMethod> filterPaymentMethods;
	
	
	private List<Event> events;
	

	@PostConstruct void init() {
		populateComboProducts();
		populateComboPaymentMethods();
	}
	
	
	private void populateComboPaymentMethods() {
		comboPaymentMethods = paymentMethodService.searchPaymentMethod();
	}


	private void populateComboProducts() {
		comboProducts = productService.searchProduct();
	}



	public void search() {
		events = reportService.searchEventByFilter(filterYear
						                          ,filterMonths
						                          ,filterProducts
						                          ,filterEventStatusList
						                          ,filterPaymentMethods
						                          );
		JSFUtil.addMessageAboutResult(events);
	}

	//acessores...
	private static final long serialVersionUID = 8997351953174610757L;
	public List<Event> getEvents() {
		return events;
	}
	public List<Integer> getFilterMonths() {
		return filterMonths;
	}
	public void setFilterMonths(List<Integer> filterMonths) {
		this.filterMonths = filterMonths;
	}
	public Integer getFilterYear() {
		return filterYear;
	}
	public void setFilterYear(Integer filterYear) {
		this.filterYear = filterYear;
	}
	public List<Product> getComboProducts() {
		return comboProducts;
	}
	public List<Product> getFilterProducts() {
		return filterProducts;
	}
	public void setFilterProducts(List<Product> filterProducts) {
		this.filterProducts = filterProducts;
	}
	public List<EventStatus> getFilterEventStatusList() {
		return filterEventStatusList;
	}
	public void setFilterEventStatusList(List<EventStatus> filterEventStatusList) {
		this.filterEventStatusList = filterEventStatusList;
	}
	public List<PaymentMethod> getFilterPaymentMethods() {
		return filterPaymentMethods;
	}
	public void setFilterPaymentMethods(List<PaymentMethod> filterPaymentMethods) {
		this.filterPaymentMethods = filterPaymentMethods;
	}
	public List<PaymentMethod> getComboPaymentMethods() {
		return comboPaymentMethods;
	}
	
}
