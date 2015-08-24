package br.com.tremn.crm.model.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Maturidade de um contato segundo suas faixa de idade
 * @author Solkam
 * @since 23 AGO 2015
 */
@Entity
public class Maturity implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=20)
	@NotNull
	private String name;
	
	@NotNull
	private Integer minAge;
	
	@NotNull
	private Integer maxAge;


	
	//acessores...
	private static final long serialVersionUID = 7449735206279342831L;
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

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
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
		Maturity other = (Maturity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	/**
	 * Verifica se uma idade está dentro do min e max da maturidade
	 * @param address
	 * @return
	 */
	public boolean getFlagInsideAges(Integer age) {
		if (age==null) return false;
		
		if (minAge <= age && age <= maxAge) {
			return true;
		} else {
			return false;
		}
	}
	
}
