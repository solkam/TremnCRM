package br.com.tremn.crm.model.entity.enumeration;

/**
 * Enum para os status de produto
 * @author Solkam
 * @since 02 MAR 2015
 */
public enum EventStatus {
	
	PLANNED("Planejado", "icon_product_status_PLANNED_36.png")
	,
	ACTIVE("Ativo", "icon_product_status_ACTIVE_36.png")
	,
	CANCELED("Cancelado", "icon_product_status_CANCELED_36.png")
	,
	CONCLUDED("Concluído", "icon_product_status_CONCLUDED_36.png")
	;
	
	
	private final String description;
	private final String img;
	
	private EventStatus(String d, String i) {
		this.description = d;
		this.img = i;
	}

	public String getDescription() {
		return description;
	}

	public String getImg() {
		return img;
	}
	

}
