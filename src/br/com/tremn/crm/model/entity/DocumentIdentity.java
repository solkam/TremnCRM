package br.com.tremn.crm.model.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Classe embutivel com os possiveis documentos de
 * um contato.
 * @author Solkam
 * @since 01 FEV 2015
 */
@Embeddable
public class DocumentIdentity implements Serializable {

	@Size(max=30)
	private String documentCPF;
	
	@Size(max=30)
	private String documentPassport;
	
	@Size(max=30)
	private String documentRG;


	//acessores...
	private static final long serialVersionUID = -274097638030534880L;
	public String getDocumentCPF() {
		return documentCPF;
	}
	public void setDocumentCPF(String documentCPF) {
		this.documentCPF = documentCPF;
	}
	public String getDocumentPassport() {
		return documentPassport;
	}
	public void setDocumentPassport(String documentPassport) {
		this.documentPassport = documentPassport;
	}
	public String getDocumentRG() {
		return documentRG;
	}
	public void setDocumentRG(String documentRG) {
		this.documentRG = documentRG;
	}
}
