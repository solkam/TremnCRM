package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.service.ContactService;

/**
 * Controller para UC Gerenciar Contato
 * @author Solkam
 * @since 01 FEV 2015
 */
@ManagedBean(name="contactMB")
@ViewScoped
public class ContactMB implements Serializable {
	
	@EJB ContactService service;
	
	
	private List<Contact> contacts;
	
	private Contact contact;
	

	//filtros
	private String filterName;
	private String filterEmail;
	private String filterCity;
	

	//inits..
	@PostConstruct void init() {
		populateContacts();
	}

	private void populateContacts() {
		contacts = service.searchContactByFilters(filterName, filterEmail, filterCity);
	}
	
	
	//actions...
	public void reset() {
		contact = new Contact();
	}
	
	public void search() {
		populateContacts();
		JSFUtil.addMessageAboutResult(contacts);
	}
	
	public void manager(Contact selectedContact) {
		this.contact = selectedContact;
		refresh();
	}
	
	public void save() {
		contact.validateBirthdate();
		service.saveContact(contact);
		populateContacts();
		refresh();
		JSFUtil.addInfoMessage("Contato salvo com sucesso");
	}
	
	
	public void remove() {
		service.removeContact(contact);
		populateContacts();
		JSFUtil.addInfoMessage("Contato removido");
	}
	
	
	//util
	private void refresh() {
		contact = service.refreshContact(contact); 
	}

	
	
	//acessores...
	private static final long serialVersionUID = -1748543442806801025L;
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public String getFilterEmail() {
		return filterEmail;
	}
	public void setFilterEmail(String filterEmail) {
		this.filterEmail = filterEmail;
	}
	public String getFilterCity() {
		return filterCity;
	}
	public void setFilterCity(String filterCity) {
		this.filterCity = filterCity;
	}
}
