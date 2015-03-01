package br.com.tremn.crm.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import br.com.tremn.crm.model.entity.enumeration.TelephoneMobileCompany;

/**
 * Classe embutivel que representa todos os 
 * telefone possiveis de um contact
 * @author Solkam
 * @since 01 FEV 2015
 */
@Embeddable
public class Telephone implements Serializable {
	
	private static final String DEFAULT_NUMBER = "+55(21)";

	private TelephoneMobileCompany telephoneMobile1Company;
	private String telephoneMobile1 = DEFAULT_NUMBER;
	
	private TelephoneMobileCompany telephoneMobile2Company;
	private String telephoneMobile2 = DEFAULT_NUMBER;

	private String telephoneCommercial = DEFAULT_NUMBER;
	
	private String telephoneResidential = DEFAULT_NUMBER;


	//acessores...
	private static final long serialVersionUID = -39202776833901706L;
	
	public String getTelephoneMobile1() {
		return telephoneMobile1;
	}
	public TelephoneMobileCompany getTelephoneMobile1Company() {
		return telephoneMobile1Company;
	}
	public void setTelephoneMobile1Company(TelephoneMobileCompany telephoneMobile1Company) {
		this.telephoneMobile1Company = telephoneMobile1Company;
	}
	public TelephoneMobileCompany getTelephoneMobile2Company() {
		return telephoneMobile2Company;
	}

	public void setTelephoneMobile2Company(TelephoneMobileCompany telephoneMobile2Company) {
		this.telephoneMobile2Company = telephoneMobile2Company;
	}

	public void setTelephoneMobile1(String telephoneMobile1) {
		this.telephoneMobile1 = telephoneMobile1;
	}
	public String getTelephoneMobile2() {
		return telephoneMobile2;
	}
	public void setTelephoneMobile2(String telephoneMobile2) {
		this.telephoneMobile2 = telephoneMobile2;
	}
	public String getTelephoneCommercial() {
		return telephoneCommercial;
	}
	public void setTelephoneCommercial(String telephoneCommercial) {
		this.telephoneCommercial = telephoneCommercial;
	}
	public String getTelephoneResidential() {
		return telephoneResidential;
	}
	public void setTelephoneResidential(String telephoneResidential) {
		this.telephoneResidential = telephoneResidential;
	}
}
