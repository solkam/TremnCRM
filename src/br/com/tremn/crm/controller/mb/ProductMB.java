package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Product;
import br.com.tremn.crm.model.entity.enumeration.ProductCategory;
import br.com.tremn.crm.model.entity.enumeration.EventStatus;
import br.com.tremn.crm.model.service.ProductService;
import br.com.tremn.crm.model.util.DateUtil;


/**
 * Controller para Gerenciar Produtos
 * @author Solkam
 * @since 02 MAR 2015
 */
@ManagedBean(name="productMB")
@ViewScoped
public class ProductMB implements Serializable {
	

	@EJB ProductService service;
	
	private List<Product> products;
	
	private Product product;
	
	
	//filtro
	private Boolean filterFlagActive = true;

	
	private void populateProducts() {
		products = service.searchProduct();
	}
	
	
	//actions...
	
	public void search() {
		populateProducts();
		JSFUtil.addMessageAboutResult(products);
	}
	
	
	public void reset() {
		product = new Product();
	}
	
	
	
	public void manage(Product selectedProduct) {
		this.product = selectedProduct;
	}
	
	
	public void save() {
		product = service.saveProduct(product);
		refresh();
		populateProducts();
		JSFUtil.addInfoMessage("Produto salvo com sucesso");
	}
	
	
	public void remove() {
		service.removeProduct(product);
		populateProducts();
		JSFUtil.addInfoMessage("Produto removido");
	}


	
	//util
	private void refresh() {
		product = service.refresthProduct(product);
	}



	
	//acessores
	private static final long serialVersionUID = -3582748635073006379L;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Product> getProducts() {
		return products;
	}


	public Boolean getFilterFlagActive() {
		return filterFlagActive;
	}


	public void setFilterFlagActive(Boolean filterFlagActive) {
		this.filterFlagActive = filterFlagActive;
	}
	
}
