package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum para as categoria de produtos da TREMN
 * @author Solkam
 * @since 02 MAR 2015
 */
public enum ProductCategory {
	
	SHORT_DURATION("Curso de curta dura��o")
	,
	LONG_DURATION("Curso de longa dura��o")
	,
	PALESTRA("Palestra")
	,
	SEMINAR("Semin�rio")
	,
	SEGUIMENT("Seguimento")
	,
	IN_COMPANY("Treinamento in_company")
	,
	COACHING("Coaching")
	;
	
	
	private final String description;
	
	private ProductCategory(String d) {
		this.description = d;
	}

	public String getDescription() {
		return description;
	}
	
	

}
