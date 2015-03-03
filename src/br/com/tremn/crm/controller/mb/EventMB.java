package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Event;
import br.com.tremn.crm.model.entity.Product;
import br.com.tremn.crm.model.entity.enumeration.EventStatus;
import br.com.tremn.crm.model.service.EventService;
import br.com.tremn.crm.model.service.ProductService;
import br.com.tremn.crm.model.util.DateUtil;

/**
 * Controller para Gerenciar Eventos
 * @author Solkam
 * @since 03 MAR 2015
 */
@ManagedBean(name="eventMB")
@ViewScoped
public class EventMB implements Serializable {
	
	@EJB EventService eventService;
	@EJB ProductService productService;
	
	private List<Event> events;
	
	private Event event;
	
	
	//filtros
	private Product filterProduct = new Product();
	private String filterName;
	private Integer filterYear = DateUtil.getCurrentYear();
	private List<EventStatus> filterStatusList;
	
	//combo
	private List<Product> comboProducts;
	
	
	//initis 
	@PostConstruct void init() {
		populateFilterStatusList();
		populateComboProducts();
	}

	private void populateComboProducts() {
		comboProducts = productService.searchProductByFlagActive( true );
	}

	private void populateFilterStatusList() {
		filterStatusList = new ArrayList<EventStatus>();
		filterStatusList.add( EventStatus.PLANNED );
		filterStatusList.add( EventStatus.ACTIVE );
	}
	
	
	private void populateEvents() {
		events = eventService.searchEventByFilter(filterProduct, filterName, filterYear, filterStatusList);
	}
	
	
	public void search() {
		populateEvents();
		JSFUtil.addMessageAboutResult(events);
	}

	
	public void reset() {
		event = new Event();
	}
	
	
	public void manage(Event seletecdEvent) {
		this.event = seletecdEvent;
	}
	
	
	public void save() {
		event = eventService.saveEvent(event);
		populateEvents();
		JSFUtil.addInfoMessage("Evento salvo com sucesso");
	}
	
	
	public void remove() {
		eventService.removeEvent(event);
		populateEvents();
		JSFUtil.addInfoMessage("Evento removido");
	}
	
	

	//util

	//acessores...
	private static final long serialVersionUID = -403844433478168660L;
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Product getFilterProduct() {
		return filterProduct;
	}
	public void setFilterProduct(Product filterProduct) {
		this.filterProduct = filterProduct;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public Integer getFilterYear() {
		return filterYear;
	}
	public void setFilterYear(Integer filterYear) {
		this.filterYear = filterYear;
	}
	public List<EventStatus> getFilterStatusList() {
		return filterStatusList;
	}
	public void setFilterStatusList(List<EventStatus> filterStatusList) {
		this.filterStatusList = filterStatusList;
	}
	public List<Event> getEvents() {
		return events;
	}
	public List<Product> getComboProducts() {
		return comboProducts;
	}
}
