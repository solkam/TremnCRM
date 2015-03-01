package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum com as categorias de participação
 * 
 * @author Solkam
 * @since 01 MAR 2015
 */
public enum ParticipationCategory {
	
	ASSOCIADO_TREMN("Associado Tremn")
	,
	PRODUTORCB_ELITE("Produtor CB - Elite")
	,
	PRODUTORCB_LIGHT("Produtor CB - Light")
	,
	CLIENT("Cliente")
	;
	
	private final String description;
	
	private ParticipationCategory(String d){
		this.description = d;
	}

	public String getDescription() {
		return description;
	}
	
	

}
