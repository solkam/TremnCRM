package br.com.tremn.crm.model.entity.enumeration;

/**
 * Representa os generos
 * @author Solkam
 * @since 01 FEV 2015
 */
public enum Gender {
	
	M("Masculino")
	,
	F("Feminino")
	;
	
	private final String description;

	
	private Gender(String d) {
		this.description = d;
	}


	public String getDescription() {
		return description;
	}
	
}
