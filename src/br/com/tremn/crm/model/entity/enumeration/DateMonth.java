package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum que representa os meses do anos
 * @author Solkam
 * @since 28 FEV 2015
 */
public enum DateMonth {
	
	JAN(1, "Janeiro")
	,
	FEB(2, "Fevereiro")
	,
	MAR(3, "Março")
	,
	APR(4, "Abril")
	,
	MAY(5, "Maio")
	,
	JUN(6, "Junho")
	,
	JUL(7, "Julho")
	,
	AUG(8, "Agosto")
	,
	SEP(9, "Setembro")
	,
	OUT(10, "Outubro")
	,
	NOV(11, "Novembro")
	,
	DEC(12, "Dezembro")
	;

	private final Integer index;
	private final String description;
	
	private DateMonth(Integer i, String d) {
		this.index = i;
		this.description = d;
	}

	public String getDescription() {
		return description;
	}

	public Integer getIndex() {
		return index;
	}
	

}
