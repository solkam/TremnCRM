package br.com.tremn.crm.controller.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.service.ContactService;

/**
 * Conversor para Contact
 * @author Solkam
 * @since 01 MAR 2015
 */
@ManagedBean(name="contactConverter")
@ViewScoped
public class ContactConverter implements Converter {

	@EJB
	private ContactService service;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String txt) {
		if (txt==null || txt.trim().isEmpty()) {
			return null;
		}
		
		long contactId = Long.parseLong( txt );
		Contact foundContact = service.findContactById( contactId );
		return foundContact;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
		if (obj==null) {
			return null;
		}
		
		Contact contact = (Contact) obj;
		return contact.getId().toString();
	}

}
