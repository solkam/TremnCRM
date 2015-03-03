package br.com.tremn.crm.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.tremn.crm.model.entity.enumeration.EventStatus;

/**
 * Representa o evento de um produto
 * @author Solkam
 * @since 02 MAR 2015
 */
@Entity
public class Event implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=100)
	private String name;
	
	
	@Temporal(TemporalType.DATE)
	private Date beginDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	
	@Enumerated(EnumType.STRING)
	private EventStatus status = EventStatus.PLANNED;
	

	@Size(max=1000)
	private String observation;
	
	
	
	@ManyToOne
	@NotNull
	private Product product;
	
	
	@OneToMany(mappedBy="event")
	private List<VinculoContactEvent> vinculos;

	
	
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
	private static final long serialVersionUID = 1261915639812541825L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<VinculoContactEvent> getVinculos() {
		return vinculos;
	}

	public void setVinculos(List<VinculoContactEvent> vinculos) {
		this.vinculos = vinculos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public EventStatus getStatus() {
		return status;
	}
	public void setStatus(EventStatus status) {
		this.status = status;
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
		Event other = (Event) obj;
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

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + "]";
	}
	
	//status
	public Boolean getFlagStatusActive() {
		return EventStatus.ACTIVE.equals( getStatus() );
	}
	public Boolean getFlagStatusCancelled() {
		return EventStatus.CANCELED.equals( getStatus() );
	}
	public Boolean getFlagStatusConcluded() {
		return EventStatus.CONCLUDED.equals( getStatus() );
	}
	public Boolean getFlagStatusPlanned() {
		return EventStatus.PLANNED.equals( getStatus() );
	}
	
	
}
