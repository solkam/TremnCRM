package br.com.tremn.crm.controller.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.Product;
import br.com.tremn.crm.model.service.ProductService;

/**
 * Converter para Produto
 * @author Solkam
 * @since 03 MAR 2015
 */
@ManagedBean(name="productConverter")
public class ProductConverter implements Converter {

	@EJB ProductService service;
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String txt) {
		if (txt==null || txt.trim().isEmpty()) return null;
		
		long prodId = Long.parseLong( txt );
		Product foundProduct = service.findProductById(prodId);
		return foundProduct;
	}

	
	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
		if (obj==null) return null;
		
		Product prod = (Product) obj;
		return String.valueOf( prod.getId() );
	}

}
