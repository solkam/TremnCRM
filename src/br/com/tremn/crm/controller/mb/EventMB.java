package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.model.entity.Event;
import br.com.tremn.crm.model.entity.enumeration.EventStatus;
import br.com.tremn.crm.model.entity.enumeration.ProductCategory;
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
	
	@EJB ProductService service;
	
	private List<Event> events;
	
	private Event event;
	
	
	//filtros
	private Integer filterAno = DateUtil.getCurrentYear();
	private List<EventStatus> filterStatusList;
	private List<ProductCategory> filterCategoryList;
	
	
	//initis 
	@PostConstruct void init() {
		populateFilterStatusList();
	}

	private void populateFilterStatusList() {
		filterStatusList = new ArrayList<EventStatus>();
		filterStatusList.add( EventStatus.PLANNED );
		filterStatusList.add( EventStatus.ACTIVE );
	}
	
	
	public void search() {
		
	}

	public void reset() {
		
	}
	
	public void manage(Event seletecdEvent) {
		this.event = seletecdEvent;
	}
	
	
	public void save() {
		
	}
	
	public void remove() {
		
	}
	
	


	//acessores...
	private static final long serialVersionUID = -403844433478168660L;
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getFilterAno() {
		return filterAno;
	}

	public void setFilterAno(Integer filterAno) {
		this.filterAno = filterAno;
	}

	public List<EventStatus> getFilterStatusList() {
		return filterStatusList;
	}

	public void setFilterStatusList(List<EventStatus> filterStatusList) {
		this.filterStatusList = filterStatusList;
	}

	public List<ProductCategory> getFilterCategoryList() {
		return filterCategoryList;
	}

	public void setFilterCategoryList(List<ProductCategory> filterCategoryList) {
		this.filterCategoryList = filterCategoryList;
	}

	public List<Event> getEvents() {
		return events;
	}
	
	
}
