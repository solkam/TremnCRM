package br.com.tremn.crm.controller.converter;

import javax.ejb.EJB;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.Event;
import br.com.tremn.crm.model.service.EventService;

/**
 * Converter para evento
 * @author Solkam
 * @since 27 OUT 2015
 */
@ManagedBean(name="eventConverter")
@ViewScoped
public class EventConverter implements Converter {

	@EJB EventService eventService;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent c, String txt) {
		if (txt==null || txt.trim().isEmpty()) {
			return null;
		}
		Long id = Long.parseLong(txt);
		Event event = eventService.findEventById(id);
		return event;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent c, Object obj) {
		if (obj==null) {
			return null;
		}
		Event event = (Event) obj;
		return String.valueOf( event.getId() );
	}

}
