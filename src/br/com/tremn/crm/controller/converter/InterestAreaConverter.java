package br.com.tremn.crm.controller.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.InterestArea;
import br.com.tremn.crm.model.service.InterestAreaService;


/**
 * Converter para Areas de Interesse
 * @author Solkam
 * @since 26 JUL 2015
 */
@ManagedBean(name="interestAreaConverter")
@ViewScoped
public class InterestAreaConverter implements Converter {

	@EJB InterestAreaService service;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String txt) {
		if (txt==null || txt.trim().isEmpty()) {
			return null;
		}
		Long areaId = Long.parseLong(txt);
		InterestArea area = service.findInterestAreaById(areaId);
		return area;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
		if (obj==null) {
			return null;
		}
		InterestArea area = (InterestArea) obj;
		return String.valueOf( area.getId() );
	}

}
