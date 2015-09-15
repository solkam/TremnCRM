package br.com.tremn.crm.model.dto;

import java.io.Serializable;

/**
 * DTO para importar contatos de arquivo CSV
 * @author Solkam
 * @since 14 SET 2015
 */
public class ImportContactDTO implements Serializable {
	
	private int key;//chave artificial
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String telephone;
	
	private String mesProducao;
	
	private String anoProducao;
	
	private String registroOriginal;

	
	//acessores...
	private static final long serialVersionUID = -4975208087486314820L;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMesProducao() {
		return mesProducao;
	}
	public void setMesProducao(String mesProducao) {
		this.mesProducao = mesProducao;
	}
	public String getAnoProducao() {
		return anoProducao;
	}
	public void setAnoProducao(String anoProducao) {
		this.anoProducao = anoProducao;
	}
	public String getRegistroOriginal() {
		return registroOriginal;
	}
	public void setRegistroOriginal(String registroOriginal) {
		this.registroOriginal = registroOriginal;
	}

}
