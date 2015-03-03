package br.com.tremn.crm.controller.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.enumeration.EventStatus;

/**
 * Conversor para Status de produto
 * @author Solkam
 * @since 02 MAR 2015
 */
@ManagedBean(name="eventStatusConverter")
public class EventStatusConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String txt) {
		if (txt==null || txt.trim().isEmpty()) return null;
		
		EventStatus status = EventStatus.valueOf(txt);
		return status;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
		if (obj==null) return null;

		EventStatus status = (EventStatus) obj;
		return status.name();
	}

}
