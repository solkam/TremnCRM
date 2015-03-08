package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum com os tipos de vinculos
 * @author Solkam
 * @since 08 MAR 2015
 */
public enum VinculoType {
	
	PARTICIPANT_FREE("Participante gratuito")
	,
	PARTICIPANT_PAID("Participante pago")
	,
	PALESTRANT("Palestrante")
	,
	INSTRUCTOR("Instrutor")
	;
	
	private final String description;
	
	private VinculoType(String d) {
		this.description = d;
	}

	public String getDescription() {
		return description;
	}

}
