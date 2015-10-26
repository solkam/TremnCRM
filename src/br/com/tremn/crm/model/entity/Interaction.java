package br.com.tremn.crm.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Interação que se faz com um contato referente a um evento
 * @author Solkam
 * @since 26 OUT 2015
 */
@Entity
public class Interaction implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@NotNull
	private Event event;
	
	
	@ManyToOne
	@NotNull
	private Contact contact;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date interactionDate;
	
	
	@NotNull
	private String responsable;
	
	
	@Size(max=1000)
	private String interactionNote;


	
	//acessores...
	private static final long serialVersionUID = -6661045156504307541L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Date getInteractionDate() {
		return interactionDate;
	}
	public void setInteractionDate(Date interactionDate) {
		this.interactionDate = interactionDate;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getInteractionNote() {
		return interactionNote;
	}
	public void setInteractionNote(String interactionNote) {
		this.interactionNote = interactionNote;
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
		Interaction other = (Interaction) obj;
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
