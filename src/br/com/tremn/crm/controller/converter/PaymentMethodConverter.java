package br.com.tremn.crm.controller.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.PaymentMethod;
import br.com.tremn.crm.model.service.PaymentMethodService;

@ManagedBean(name="paymentMethodConverter")
@ViewScoped
public class PaymentMethodConverter implements Converter {

	@EJB PaymentMethodService service;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String txt) {
		if (txt==null || txt.trim().isEmpty()) {
			return null;
		}
		long id = Long.parseLong( txt );
		PaymentMethod method = service.findPaymentMethod(id);
		return method;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
		if (obj==null) {
			return null;
		}
		PaymentMethod method = (PaymentMethod) obj;
		return String.valueOf( method.getId() );
	}

}
