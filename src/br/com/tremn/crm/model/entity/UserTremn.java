package br.com.tremn.crm.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.sun.istack.internal.NotNull;

import br.com.tremn.crm.model.entity.enumeration.Profile;

/**
 * Representa o usuario do sistema, sua senha, perfil e 
 * seus dados de contato
 * @author Solkam
 * @since 21 JAN 2015
 */
@Entity
@SequenceGenerator(name="UserTremSeq", sequenceName="UserTremSeq")
public class UserTremn implements Serializable {
	

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UserTremSeq")
	private Long id;
	
	@NotNull
	private String password;
	
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Profile profile;
	
	
	@OneToOne
	@NotNull
	private Contact contact;


	
	//acessores...
	private static final long serialVersionUID = 109826895261155441L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
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
		UserTremn other = (UserTremn) obj;
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
