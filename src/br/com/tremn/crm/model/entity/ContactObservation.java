package br.com.tremn.crm.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Observação sobre o contato com data e responsável
 * @author Solkam
 * @since 26 OUT 2016
 */
@Entity
public class ContactObservation implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@NotNull
	private Contact contact;
	

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date observationDate;
	
	
	@Size(max=1000)
	@NotNull
	private String observation;
	
	
	@NotNull
	private String responsable;

	

	//acessores...
	private static final long serialVersionUID = -6612355601926202763L;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}


	public Date getObservationDate() {
		return observationDate;
	}


	public void setObservationDate(Date observationDate) {
		this.observationDate = observationDate;
	}


	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}


	public String getResponsable() {
		return responsable;
	}


	public void setResponsable(String responsable) {
		this.responsable = responsable;
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
		ContactObservation other = (ContactObservation) obj;
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
