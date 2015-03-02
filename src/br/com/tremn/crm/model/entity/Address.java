package br.com.tremn.crm.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import br.com.tremn.crm.model.entity.enumeration.AddressType;

/**
 * Classe embutivel que representa todos os
 * campos de endereço
 * @author Solkam
 * @since 01 FEV 2015
 */
@Embeddable
public class Address implements Serializable {
	
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	@Size(max=100)
	private String addressStreet;
	
	@Size(max=30)
	private String addressNumber;
	
	@Size(max=50)
	private String addressComplement;
	
	@Size(max=100)
	private String addressDistrict;
	
	@Size(max=100)
	private String addressCity;
	
	@Size(max=50)
	private String addressState;
	
	@Size(max=20)
	private String addressZipcode;


	//acessores...
	private static final long serialVersionUID = 8445223631108980610L;
	public String getAddressStreet() {
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}
	public AddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	public String getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}
	public String getAddressComplement() {
		return addressComplement;
	}
	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}
	public String getAddressDistrict() {
		return addressDistrict;
	}
	public void setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public String getAddressState() {
		return addressState;
	}
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}
	public String getAddressZipcode() {
		return addressZipcode;
	}
	public void setAddressZipcode(String addressZipcode) {
		this.addressZipcode = addressZipcode;
	}
}
