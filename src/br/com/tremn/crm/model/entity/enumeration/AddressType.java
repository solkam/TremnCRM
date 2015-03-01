package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum para os tipos de logradouros
 * @author Solkam
 * @since 28 FEV 2015
 */
public enum AddressType {
	
	STREET("Rua")
	,
	AVENUE("Avenida")
	,
	ALAMEDA("Alameda")
	,
	BOULEVARD("Boulevard")
	,
	TRAVESSA("Travessa")
	,
	BECO("Beco")
	;
	
	
	private final String description;
	
	
	private AddressType(String d) {
		this.description = d;
	}


	public String getDescription() {
		return description;
	}

}
