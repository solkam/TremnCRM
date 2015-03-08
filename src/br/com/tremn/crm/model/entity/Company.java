package br.com.tremn.crm.model.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Classe embarcavel que representa a empresa
 * onde o contato trabalha
 * @author Solkam
 * @since 01 MAR 2015
 */
@Embeddable
public class Company  {

	/**
	 * Empresa em que trabalha
	 */
	@Size(max=100)
	private String companyName;
	
	/**
	 * Cargo na empresa
	 */
	@Size(max=100)
	private String companyFunction;


	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyFunction() {
		return companyFunction;
	}

	public void setCompanyFunction(String companyFunction) {
		this.companyFunction = companyFunction;
	}
}
