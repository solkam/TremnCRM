package br.com.tremn.crm.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.tremn.crm.model.entity.enumeration.Gender;
import br.com.tremn.crm.model.entity.enumeration.ParticipationCategory;
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
	 * Ultimo nome (ou nome de familia)
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
	 * Email secund�rio ou alternativo
	 */
	@Size(max=100)
	private String emailAlternative;
	
	/**
	 * Dados completos de endereco
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
	 * Mes do nascimento
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
	 * Profissoes desempenhadas
	 */
	@ManyToMany
	@JoinTable(name="Contact_x_Profession"
		,joinColumns=@JoinColumn(name="contact_id")
		,inverseJoinColumns=@JoinColumn(name="profession_id")
	)
	private List<Profession> professions;
	
	
	/**
	 * Areas de interesse (hobbies)
	 */
	@ManyToMany
	@JoinTable(name="Contact_x_InterestArea"
		,joinColumns=@JoinColumn(name="contact_id")
		,inverseJoinColumns=@JoinColumn(name="interestArea_id")
	)
	private List<InterestArea> interestAreas;


	/**
	 * Empresa em trbalha e funcao
	 */
	@Embedded
	private Company company;
	
	/**
	 * Categoria de participacao em eventos da Tremn
	 */
	@Enumerated(EnumType.STRING)
	private ParticipationCategory participationCategory = ParticipationCategory.CLIENT;
	
	
	/**
	 * Contact responsavel por indicar
	 */
	@ManyToOne
	private Contact contactWhoIndicated;
	
	/**
	 * Dados sobre as redes sociais
	 */
	@Embedded
	private SocialNetwork socialNetwork;
	
	/**
	 * Observacoes diversas
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
	public List<InterestArea> getInterestAreas() {
		return interestAreas;
	}
	public void setInterestAreas(List<InterestArea> interestAreas) {
		this.interestAreas = interestAreas;
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
	public List<Profession> getProfessions() {
		return professions;
	}
	public void setProfessions(List<Profession> professions) {
		this.professions = professions;
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
	 * Monta um Date a partir dos campos individuais de ano, mes e dia
	 * @return
	 */
	public Date getBirthdate() {
		return DateUtil.buildDate(getBirthYear(), getBirthMonth(), getBirthDay());
	}
	
	/**
	 * Calcula a idade atraves da data de nascimento
	 * @return
	 */
	public Integer getAge() {
		if (getBirthdate()!=null) {
			return DateUtil.calculateAge( getBirthdate() );
		}
		return null;
	}
	
	/**
	 * Monta o nome completo juntando o primeiro e ultimo nomes.
	 * @return
	 */
	public String getFullName() {
		return String.format("%s %s", getFirstName(), getLastName() );
	}

	
	/**
	 * Flag que valida que tanto extensao e binario da imagem estao preenchido
	 * @return
	 */
	public Boolean getFlagImageOK() {
		return getImageExtension()!=null 
			&& !getImageExtension().trim().isEmpty() 
			&& getImageBinary()!=null;
	}

	
	/**
	 * Monta o nome da imagem usando o ID e a extensao
	 * @return
	 */
	public String getImageName() {
		return String.format("contact_%s.%s", getId(), getImageExtension());
	}

	/**
	 * Os campos individuais de ano, mes e dia deve formar uma data valida
	 */
	public void validateBirthdate() {
		if (!DateUtil.isAValidDate(getBirthYear(), getBirthMonth(), getBirthDay()) ) {
			throw new BusinessException("Data de Nascimento invalida");
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
