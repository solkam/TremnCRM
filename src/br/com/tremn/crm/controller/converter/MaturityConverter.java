package br.com.tremn.crm.controller.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.Maturity;
import br.com.tremn.crm.model.service.MaturityService;

/**
 * Converter para maturidade
 * @author Solkam
 * @since 28 OUT 2015
 */
@ManagedBean(name="maturityConverter")
@ViewScoped
public class MaturityConverter implements Converter {

	@EJB MaturityService service;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent cp, String txt) {
		if (txt==null || txt.trim().isEmpty()) {
			return null;
		}
		long id = Long.parseLong(txt);
		return service.findMaturityById(id);
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent cp, Object obj) {
		if (obj==null) {
			return null;
		}
		Maturity maturity = (Maturity) obj;
		return String.valueOf( maturity.getId() );
	}

}
