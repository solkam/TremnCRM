package br.com.tremn.crm.model.service;

import static br.com.tremn.crm.model.util.QueryUtil.isNotEmpty;
import static br.com.tremn.crm.model.util.QueryUtil.isNotNull;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.tremn.crm.model.entity.Product;
import br.com.tremn.crm.model.entity.enumeration.ProductCategory;
import br.com.tremn.crm.model.exception.BusinessException;


/**
 * Serviços de negócio para Produto
 * @author Solkam
 * @since 02 MAR 2015
 */
@Stateless
public class ProductService {
	
	@PersistenceContext EntityManager manager;
	
	
	/* *******
	 * Produto
	 *********/
	
	/**
	 * Salva o produto, aplicando RN
	 * @param product
	 * @return
	 */
	public Product saveProduct(Product product) {
		verifyProductNameUnique(product);
		return manager.merge( product );
	}
	
	
	/**
	 * Verifica se nome do produto é único
	 * @param product
	 */
	private void verifyProductNameUnique(Product product) {
		Product foundProduct = findProductByName( product.getName() );
		if (foundProduct!=null && !product.equals(foundProduct)) {
			throw new BusinessException("Já existe produto com este nome");
		}
	}


	/**
	 * Remove o produto, aplicando RN
	 * @param product
	 */
	public void removeProduct(Product product) {
		product = manager.merge( product );
		manager.remove( product );
	}

	
	/**
	 * Recarrega product 
	 * @param product
	 */
	public Product refresthProduct(Product product) {
		product = manager.find(Product.class, product.getId() );
		return product;
	}
	

	/**
	 * Busca um produto pelo nome
	 * @param name
	 * @return
	 */
	private Product findProductByName(String name) {
		try {
			return manager.createNamedQuery("findProductByName", Product.class)
					.setParameter("pName", name)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	/**
	 * Pesquisa todos os produtos
	 * @return
	 */
	public List<Product> searchProduct() {
		return manager.createNamedQuery("searchProduct", Product.class)
				.getResultList();
	}
	
	
	/**
	 * Pesquia produto segundo filtros de pesquisa usando criteria
	 * @param statusList
	 * @param categoryList
	 * @param ano
	 * @return
	 */
	public List<Product> searchProductByFilter(Boolean flagActive, List<ProductCategory> categoryList) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		Predicate conjunction = builder.conjunction();
		//1.flag ativo
		if (isNotNull(flagActive)) {
			conjunction = builder.and(conjunction,
					builder.equal( root.<Boolean>get("flagActive"), flagActive)
				);
		}
		//2.category
		if (isNotEmpty(categoryList) ) {
			conjunction = builder.and(conjunction, 
					root.<ProductCategory>get("category").in(categoryList) 
				);
		}
		criteria.where( conjunction );
		criteria.orderBy( builder.asc(root.<String>get("name")) );
		
		List<Product> products = manager.createQuery(criteria).getResultList();
		return products;
	}
	

	/**
	 * Pesquisa produtos pelo flag ativo
	 * @param b
	 * @return
	 */
	public List<Product> searchProductByFlagActive(Boolean flagActive) {
		return manager.createNamedQuery("searchProductByFlagActive", Product.class)
				.setParameter("pFlagActive", flagActive)
				.getResultList()
				;
	}

}