package br.com.tremn.crm.controller.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.Profession;
import br.com.tremn.crm.model.service.ProfessionService;

/**
 * Converter para profissoes
 * @author Solkam
 * @since 27 JUL 2015
 */
@ManagedBean(name="professionConverter")
public class ProfessionConverter implements Converter {

	@EJB ProfessionService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String txt) {
		if (txt==null || txt.trim().isEmpty()) {
			return null;
		}
		
		Long id = Long.parseLong(txt);
		Profession profession = service.findProfessionById(id);
		return profession;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object == null) {
			return null;
		}
		Profession profession = (Profession) object;
		return String.valueOf( profession.getId() ); 
	}

}
