package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.mb.security.SessionHolder;
import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Event;
import br.com.tremn.crm.model.entity.Interaction;
import br.com.tremn.crm.model.entity.PaymentMethod;
import br.com.tremn.crm.model.entity.Product;
import br.com.tremn.crm.model.entity.Vinculo;
import br.com.tremn.crm.model.entity.enumeration.EventStatus;
import br.com.tremn.crm.model.service.EventService;
import br.com.tremn.crm.model.service.PaymentMethodService;
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
	@EJB PaymentMethodService paymentMethodService;
	
	@ManagedProperty("#{sessionHolder}")
	private SessionHolder sessionHolder;
	
	private List<Event> events;
	
	private Event event;
	
	
	//filtros
	private Product filterProduct;
	private String filterName;
	private Integer filterYear = DateUtil.getCurrentYear();
	private List<EventStatus> filterStatusList;
	
	//combo
	private List<Product> comboProducts;
	private List<PaymentMethod> comboPaymentMethod;
	
	
	//initis 
	@PostConstruct void init() {
		populateFilterStatusList();
		populateComboProducts();
		populateComboPaymentMethods();
		search();
		resetVinculo();
		resetInteraction();
	}

	private void populateComboPaymentMethods() {
		comboPaymentMethod = paymentMethodService.searchPaymentMethodByFlagActive(true);
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
	

	//actions...
	
	public void search() {
		populateEvents();
		JSFUtil.addMessageAboutResult(events);
	}

	
	public void reset() {
		event = new Event();
	}
	
	
	public void manage(Event seletecdEvent) {
		this.event = seletecdEvent;
		refresh();
	}
	
	

	public void save() {
		event = eventService.saveEvent(event);
		refresh();
		populateEvents();
		JSFUtil.addInfoMessage("Evento salvo com sucesso");
	}
	
	
	public void remove() {
		eventService.removeEvent(event);
		refresh();
		populateEvents();
		JSFUtil.addInfoMessage("Evento removido");
	}
	
	
	/* ******
	 * Status
	 ********/
	public void doPlanned() {
		changeStatus(EventStatus.PLANNED);
	}
	
	public void doActive() {
		changeStatus(EventStatus.ACTIVE);
	}
	
	public void doCanceled() {
		changeStatus(EventStatus.CONCLUDED);
	}
	
	public void doConcluded() {
		changeStatus(EventStatus.CONCLUDED);
	}
	
	private void changeStatus(EventStatus newStatus) {
		event.setStatus( newStatus );
		event = eventService.saveEvent(event);
		refresh();
		populateEvents();
		JSFUtil.addInfoMessage("Status de evento atualizado com sucesso");
	}
	
	
	
	/* ********
	 * Vinculos
	 **********/
	private Vinculo newVinculo;
	
	private void resetVinculo() {
		newVinculo = new Vinculo();
	}
	
	public void addVinculo() {
		newVinculo.setEvent( event );
		eventService.saveVinculo( newVinculo );
		resetVinculo();
		refresh();
		JSFUtil.addInfoMessage("Vínculo adicionado com sucesso");
	}
	
	public void removeVinculo(Vinculo selectedVinculo) {
		eventService.removeVinculo( selectedVinculo );
		refresh();
		JSFUtil.addInfoMessage("Vínculo removido");
	}
	
	
	
	/* **********
	 * Interações
	 ************/
	private Interaction newInteraction;
	
	private void resetInteraction() {
		newInteraction = new Interaction();
		newInteraction.setResponsable( sessionHolder.getUser().getEmail() );
	}
	
	public void addInteraction() {
		newInteraction.setEvent(event);
		newInteraction.setInteractionDate( new Date() );
		eventService.saveInteraction(newInteraction);
		resetInteraction();
		refresh();
		JSFUtil.addInfoMessage("Interação salva com sucesso");
	}
	
	
	public void removeInteraction(Interaction selectedInteraction) {
		eventService.removeInteraction(selectedInteraction);
		refresh();
		JSFUtil.addInfoMessage("Interação removida");
	}
	
	
	
	//util
	private void refresh() {
		event = eventService.refreshEvent(event);
		redefinePaymentMethods();
	}

	
	private void redefinePaymentMethods() {
		//redefine possiblePaymentMethods para evitar LIE:
		List<PaymentMethod> paymentMethodsAsPersistBag = event.getPossiblePaymentMethods();
		List<PaymentMethod> redefinedPaymentMethods = new ArrayList<PaymentMethod>();
		redefinedPaymentMethods.addAll( paymentMethodsAsPersistBag );
		event.setPossiblePaymentMethods( redefinedPaymentMethods );
	}
	
	

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

	public Vinculo getNewVinculo() {
		return newVinculo;
	}

	public void setNewVinculo(Vinculo newVinculo) {
		this.newVinculo = newVinculo;
	}

	public List<PaymentMethod> getComboPaymentMethod() {
		return comboPaymentMethod;
	}

	public Interaction getNewInteraction() {
		return newInteraction;
	}

	public void setNewInteraction(Interaction newInteraction) {
		this.newInteraction = newInteraction;
	}

	public void setSessionHolder(SessionHolder sessionHolder) {
		this.sessionHolder = sessionHolder;
	}
	
	
	
}
