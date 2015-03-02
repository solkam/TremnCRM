package br.com.tremn.crm.controller.mb;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.com.tremn.crm.controller.util.ImageStreamUtil;
import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.service.ContactService;

/**
 * Controller para UC Gerenciar Contato
 * @author Solkam
 * @since 01 FEV 2015
 */
@ManagedBean(name="contactMB")
@ViewScoped
public class ContactMB implements Serializable {
	
	@EJB ContactService service;

	
	private ImageStreamUtil imageStreamUtil = new ImageStreamUtil();
	
	private List<Contact> contacts;
	
	private Contact contact;
	

	//filtros
	private String filterName;
	private String filterEmail;
	private String filterCity;
	

	//inits..
	@PostConstruct void init() {
		populateContacts();
	}

	private void populateContacts() {
		contacts = service.searchContactByFilters(filterName, filterEmail, filterCity);
	}
	
	
	//actions...
	public void reset() {
		contact = new Contact();
	}
	
	public void search() {
		populateContacts();
		JSFUtil.addMessageAboutResult(contacts);
	}
	
	public void manager(Contact selectedContact) {
		this.contact = selectedContact;
		refresh();
	}
	
	public void save() {
		//validações
		contact.validateBirthdate();
		contact.validateDocuments();
		//salva
		service.saveContact(contact);
		//prepara exibição
		populateContacts();
		refresh();
		JSFUtil.addInfoMessage("Contato salvo com sucesso");
	}

	
	
	public void remove() {
		service.removeContact(contact);
		populateContacts();
		JSFUtil.addInfoMessage("Contato removido");
	}
	
	
	//util
	private void refresh() {
		contact = service.refreshContact(contact); 
	}

	
	//autocomplete
	public List<Contact> completeContact(String fragment) {
		List<Contact> contacts = service.searchContactByFistNameOrLastNameOrCity(fragment, fragment);
		return contacts;
	}
	
	
	
	/* ****
	 * FOTO
	 ******/
	/**
	 * Orquesta o upload da imagem
	 * @param event
	 * @throws IOException
	 */
	public void onFileUpload(FileUploadEvent event) throws IOException {
		redimImage(event);
		saveImageInFS();
		saveImageInDB();
	}
	
	
	/**
	 * Realiza alguns ajustes na imagem apos o upload como 
	 * redimensioná-la e gravar no disco.
	 * @param event
	 * @throws IOException
	 */
	private void redimImage(FileUploadEvent event) throws IOException {
		//1.extensão da imagem
		String imageExtension = imageStreamUtil.extractExtension( event.getFile().getFileName() );
		contact.setImageExtension(imageExtension);

		//2.conteudo binário da imagem
		InputStream imageInputStream = event.getFile().getInputstream();
		byte[] imageBinary = imageStreamUtil.getBinaryDimensionated(imageInputStream, imageExtension);
		contact.setImageBinary( imageBinary );
	}
	
	
	/**
	 * Grava no disco a imagem do contact.
	 * Se ele não tiver imagem, não grava nada.
	 * @param produto
	 */
	private void saveImageInFS() throws IOException {
		if (contact.getFlagImageOK() ) {
			byte[] imageBinary = contact.getImageBinary();
			String imageName = contact.getImageName();
		
			imageStreamUtil.writeInFileSystem(imageBinary, imageName);
		}
	}
	
	/**
	 * Salva o contact que implicitamente salvará sua imagem
	 */
	public void saveImageInDB() {
		contact = service.saveContact( contact );
		refresh();
		populateContacts();
		JSFUtil.addInfoMessage("Foto salva com sucesso");
	}
	
	
	/**
	 * Remove as informaçoes da imagem
	 * Nota: fisicamente a imagem continua no FileSystem
	 */
	public void removeImage() {
		contact.setImageBinary( null );
		contact.setImageExtension( null );
		contact = service.saveContact( contact );
		refresh();
		populateContacts();
		JSFUtil.addInfoMessage("Foto removida");
	}
	
	
	
	//acessores...
	private static final long serialVersionUID = -1748543442806801025L;
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public String getFilterEmail() {
		return filterEmail;
	}
	public void setFilterEmail(String filterEmail) {
		this.filterEmail = filterEmail;
	}
	public String getFilterCity() {
		return filterCity;
	}
	public void setFilterCity(String filterCity) {
		this.filterCity = filterCity;
	}
}
