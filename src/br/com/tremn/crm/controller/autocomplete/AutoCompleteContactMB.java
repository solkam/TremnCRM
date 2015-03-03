package br.com.tremn.crm.controller.autocomplete;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.service.ContactService;

/**
 * Controller para gerenciar os auto completes de contatos
 * @author Solkam
 * @since 03 MAR 2015
 */
@ManagedBean(name="autoCompleteContactMB")
@ViewScoped
public class AutoCompleteContactMB implements Serializable {

	@EJB ContactService service;
	
	public List<Contact> completeContact(String fragment) {
		List<Contact> contacts = service.searchContactByFistNameOrLastNameOrCity(fragment, fragment);
		return contacts;
	}
	

	
	private static final long serialVersionUID = 3494962431963193392L;
}
