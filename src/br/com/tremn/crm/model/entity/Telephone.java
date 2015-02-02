package br.com.tremn.crm.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe embutivel que representa todos os 
 * telefone possiveis de um contact
 * @author Solkam
 * @since 01 FEV 2015
 */
@Embeddable
public class Telephone implements Serializable {

	private String telephoneMobile;
	
	private String telephoneCommercial;
	
	private String telephoneResidential;


	//acessores...
	private static final long serialVersionUID = -39202776833901706L;
	public String getTelephoneMobile() {
		return telephoneMobile;
	}
	public void setTelephoneMobile(String telephoneMobile) {
		this.telephoneMobile = telephoneMobile;
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
