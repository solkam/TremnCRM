package br.com.tremn.crm.controller.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.tremn.crm.model.entity.enumeration.ProductCategory;

/**
 * Conversor para Categoria de Produto
 * @author Solkam
 * @since 02 MAR 2015
 */
@ManagedBean(name="productCategoryConverter")
public class ProductCategoryConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent c, String txt) {
		if (txt==null || txt.trim().isEmpty()) return null;
		
		ProductCategory category = ProductCategory.valueOf(txt);
		return category;
	}

	@Override
	public String getAsString(FacesContext txt, UIComponent c, Object obj) {
		if (obj==null) return null;
		
		ProductCategory category = (ProductCategory) obj;
		return category.name();
	}
	
	

}
