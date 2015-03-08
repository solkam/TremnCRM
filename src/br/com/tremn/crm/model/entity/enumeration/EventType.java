package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum para os tipos de eventos
 * @author Solkam
 * @since 02 MAR 2015
 */
public enum EventType {
	
	PALESTRA("Palestra")
	,
	OUTDOOR("Outdoor")
	,
	SEMINAR("Seminário")
	,
	SEGUIMENT("Seguimento")
	;
	
	
	private final String description;
	
	private EventType(String d) {
		this.description = d;
	}

	public String getDescription() {
		return description;
	}
	
	

}
