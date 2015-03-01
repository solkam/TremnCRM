package br.com.tremn.crm.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.tremn.crm.model.entity.enumeration.Gender;
import br.com.tremn.crm.model.exception.BusinessException;
import br.com.tremn.crm.model.util.DateUtil;


/**
 * Representa um contato ou pessoa
 * @author Solkam
 * @since 21 JAN 2015
 */
@Entity
public class Contact implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	
	@NotNull
	private String emailPrincipal;
	
	private String emailAlternative;
	
	
	@Embedded
	private Address address;
	
	@Embedded
	private DocumentIdentity document;

	@Embedded
	private Telephone telephone;
	

	@NotNull
	private Integer birthDay;
	
	@NotNull
	private Integer birthMonth;

	@NotNull
	private Integer birthYear;
	
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	private String profession;
	
	@Size(max=1000)
	private String observation;
	
	
	//log
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	
	//listener
	@PrePersist void onPersist() {
		this.createDate = new Date();
	}
	
	@PreUpdate void onUpdate() {
		this.updateDate = new Date();
	}
	
	
	
	
	//acessores...
	private static final long serialVersionUID = -8956195215009970993L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmailPrincipal() {
		return emailPrincipal;
	}

	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}
	public String getEmailAlternative() {
		return emailAlternative;
	}
	public void setEmailAlternative(String emailAlternative) {
		this.emailAlternative = emailAlternative;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		if (address==null) {
			address = new Address();
		}
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public DocumentIdentity getDocument() {
		if (document==null) {
			document = new DocumentIdentity();
		}
		return document;
	}
	public void setDocument(DocumentIdentity document) {
		this.document = document;
	}
	public Telephone getTelephone() {
		if (telephone==null) {
			telephone = new Telephone();
		}
		return telephone;
	}
	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}
	

	public Integer getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Integer birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(Integer birthMonth) {
		this.birthMonth = birthMonth;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
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
	
	public boolean isTransient() {
		return getId()==null;
	}
	
	
	
	//runtime
	
	public Date getBirthdate() {
		return DateUtil.buildDate(getBirthYear(), getBirthMonth(), getBirthDay());
	}
	
	
	public Integer getAge() {
		if (getBirthdate()!=null) {
			return DateUtil.calculateAge( getBirthdate() );
		}
		return null;
	}
	
	public String getFullName() {
		return String.format("%s %s", getFirstName(), getLastName() );
	}


	public void validateBirthdate() {
		if (!DateUtil.isAValidDate(getBirthYear(), getBirthMonth(), getBirthDay()) ) {
			throw new BusinessException("Data de Nascimento inválida");
		}
	}
	
	
}
