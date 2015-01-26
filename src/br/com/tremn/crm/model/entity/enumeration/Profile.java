package br.com.tremn.crm.model.entity.enumeration;

/**
 * Perfis de acesso de usuarios
 * @author Solkam
 * @since 24 JAN 2015
 */
public enum Profile {
	
	ADM("Administrador")
	,
	OPE("Operador")
	;

	
	private final String description;
	
	private Profile(String d) {
		this.description = d;
	}

	public String getDescription() {
		return description;
	}

}
