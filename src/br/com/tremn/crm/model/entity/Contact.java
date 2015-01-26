package br.com.tremn.crm.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;


/**
 * Representa um contato ou pessoa
 * @author Solkam
 * @since 21 JAN 2015
 */
@Entity
@SequenceGenerator(name="ContactSeq", sequenceName="ContactSeq")
public class Contact implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ContactSeq")
	private Long id;

	@NotNull
	private String name;
	
	@NotNull
	private String email;

	
	
	//acessores...
	private static final long serialVersionUID = -8956195215009970993L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		Contact other = (Contact) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
