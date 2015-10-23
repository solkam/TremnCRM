package br.com.tremn.crm.model.entity;

import java.io.Serializable;





import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.tremn.crm.model.entity.enumeration.VinculoType;

/**
 * Representa os eventos que um contato participa
 * @author Solkam
 * @since 03 MAR 2015
 */
@Entity
public class VinculoContactEvent implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@NotNull
	private Contact contact;
	
	
	@ManyToOne
	@NotNull
	private Event event;
	
	
	@Enumerated(EnumType.STRING)
	private VinculoType type;
	
	
	@ManyToOne
	private PaymentMethod paymentMethod;
	
	
	private BigDecimal paymentParcelValue; 
	
	
	@Size(max=100)
	private String observation;

	
	
	//acessores
	private static final long serialVersionUID = -8196059119642320272L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public BigDecimal getPaymentParcelValue() {
		return paymentParcelValue;
	}
	public void setPaymentParcelValue(BigDecimal paymentParcelValue) {
		this.paymentParcelValue = paymentParcelValue;
	}
	public VinculoType getType() {
		return type;
	}
	public void setType(VinculoType type) {
		this.type = type;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VinculoContactEvent other = (VinculoContactEvent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public boolean isTransient() {
		return getId()==null;
	}
}
