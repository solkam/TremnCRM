package br.com.tremn.crm.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Cartão de negocio do contact
 * @author Solkam
 * @since 29 OUT 2015
 */
@Entity
public class ContactBusinessCard implements Serializable {
	
	public static final int W_DIM = 320;
	public static final int H_DIM = 170;
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	@ManyToOne
	@NotNull
	private Contact contact;
	
	
	@Lob
	private byte[] imageBinary;
	
	
	@NotNull
	private String imageExtension;

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;


	//listener
	@PrePersist void onPersist() {
		this.uploadDate = new Date();
	}
	
	
	//acessores...
	private static final long serialVersionUID = -8031471673434804961L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public byte[] getImageBinary() {
		return imageBinary;
	}
	public void setImageBinary(byte[] imageBinary) {
		this.imageBinary = imageBinary;
	}
	public String getImageExtension() {
		return imageExtension;
	}
	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
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
		ContactBusinessCard other = (ContactBusinessCard) obj;
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
	
	
	
	//runtime
	public String getImageName() {
		return String.format("businessCard_%s.%s", getId(), getImageExtension() );
	}
	
}
