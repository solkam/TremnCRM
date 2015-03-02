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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.tremn.crm.model.entity.enumeration.Gender;
import br.com.tremn.crm.model.entity.enumeration.ParticipationCategory;
import br.com.tremn.crm.model.entity.enumeration.Profession;
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

	/**
	 * Primeiro nome
	 */
	@NotNull
	@Size(max=100)
	private String firstName;
	
	/**
	 * Ultimo nome (ou nome de família)
	 */
	@NotNull
	@Size(max=100)
	private String lastName;
	
	/**
	 * Email principal, mais usado
	 */
	@NotNull
	@Size(max=100)
	private String emailPrincipal;
	
	/**
	 * Email secundário ou alternativo
	 */
	@Size(max=100)
	private String emailAlternative;
	
	/**
	 * Dados completos de endereço
	 */
	@Embedded
	private Address address;
	
	/**
	 * Dados completos de documentos
	 */
	@Embedded
	private DocumentIdentity document;

	/**
	 * Dados completos de telefones
	 */
	@Embedded
	private Telephone telephone;
	
	/**
	 * Dia do nascimento
	 */
	@NotNull
	private Integer birthDay;
	
	/**
	 * Mês do nascimento
	 */
	@NotNull
	private Integer birthMonth;

	/**
	 * Ano do nascimento
	 */
	@NotNull
	private Integer birthYear;
	
	/**
	 * Sexo
	 */
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	/**
	 * Profissão segundo Código Brasileiro de Ocupações
	 */
	@Enumerated(EnumType.STRING)
	private Profession profession;

	/**
	 * Empresa em trbalha e função
	 */
	@Embedded
	private Company company;
	
	/**
	 * Categoria de participação em eventos da Tremn
	 */
	@Enumerated(EnumType.STRING)
	private ParticipationCategory participationCategory = ParticipationCategory.CLIENT;
	
	
	/**
	 * Contact responsável por indicar
	 */
	@ManyToOne
	private Contact contactWhoIndicated;
	
	/**
	 * Dados sobre as redes sociais
	 */
	@Embedded
	private SocialNetwork socialNetwork;
	
	/**
	 * Observações diversas
	 */
	@Size(max=1000)
	private String observation;
	
	
	//foto
	@Lob
	private byte[] imageBinary;
	
	@Size(max=5)
	private String imageExtension;
	
	
	
	//logs
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
	public Company getCompany() {
		if (company==null) {
			company = new Company();
		}
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
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
	public Profession getProfession() {
		return profession;
	}
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	public Contact getContactWhoIndicated() {
		return contactWhoIndicated;
	}
	public void setContactWhoIndicated(Contact contactWhoIndicated) {
		this.contactWhoIndicated = contactWhoIndicated;
	}
	public ParticipationCategory getParticipationCategory() {
		return participationCategory;
	}
	public void setParticipationCategory(ParticipationCategory participationCategory) {
		this.participationCategory = participationCategory;
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
	public SocialNetwork getSocialNetwork() {
		if (socialNetwork==null) {
			socialNetwork = new SocialNetwork();
		}
		return socialNetwork;
	}
	public void setSocialNetwork(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
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

	/**
	 * Monta um Date a partir dos campos individuais de ano, mês e dia
	 * @return
	 */
	public Date getBirthdate() {
		return DateUtil.buildDate(getBirthYear(), getBirthMonth(), getBirthDay());
	}
	
	/**
	 * Calcula a idade através da data de nascimento
	 * @return
	 */
	public Integer getAge() {
		if (getBirthdate()!=null) {
			return DateUtil.calculateAge( getBirthdate() );
		}
		return null;
	}
	
	/**
	 * Monta o nome completo juntando o primeiro e último nomes.
	 * @return
	 */
	public String getFullName() {
		return String.format("%s %s", getFirstName(), getLastName() );
	}

	
	/**
	 * Flag que valida que tanto extensão e binario da imagem estão preenchido
	 * @return
	 */
	public Boolean getFlagImageOK() {
		return getImageExtension()!=null 
			&& !getImageExtension().trim().isEmpty() 
			&& getImageBinary()!=null;
	}

	
	/**
	 * Monta o nome da imagem usando o ID e a extensão
	 * @return
	 */
	public String getImageName() {
		return String.format("contact_%s.%s", getId(), getImageExtension());
	}

	/**
	 * Os campos individuais de ano, mês e dia deve formar uma data válida
	 */
	public void validateBirthdate() {
		if (!DateUtil.isAValidDate(getBirthYear(), getBirthMonth(), getBirthDay()) ) {
			throw new BusinessException("Data de Nascimento inválida");
		}
	}
	
	/**
	 * Pelo menos um documento tem que ser fornecido
	 */
	public void validateDocuments() {
		String cpf = getDocument().getDocumentCPF();
		String passport = getDocument().getDocumentPassport();
		String rg = getDocument().getDocumentRG();
		
		boolean isCpfNull = cpf==null || cpf.trim().isEmpty();
		boolean isPassportNull = passport==null || passport.trim().isEmpty();
		boolean isRg = rg==null || rg.trim().isEmpty();
		
		if (isCpfNull  && isPassportNull && isRg) {
			throw new BusinessException("Pelo menos um documento deve ser informado");
		}
	}
	
	
}
