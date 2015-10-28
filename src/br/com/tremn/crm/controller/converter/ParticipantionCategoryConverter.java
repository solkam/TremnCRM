package br.com.tremn.crm.controller.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.enumeration.ParticipationCategory;

/**
 * Converter para o enum Categoria de Parcipação
 * @author Solkam
 * @since 28 OUT 2015
 */
@ManagedBean(name="participationCategoryConverter")
@ViewScoped
public class ParticipantionCategoryConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String txt) {
		if (txt==null || txt.trim().isEmpty()) {
			return null;
		}
		ParticipationCategory category = ParticipationCategory.valueOf(txt);
		return category;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
		if (obj==null) {
			return null;
		}
		ParticipationCategory category = (ParticipationCategory) obj;
		return category.name();
	}
}
