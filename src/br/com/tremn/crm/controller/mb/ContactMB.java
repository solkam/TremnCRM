package br.com.tremn.crm.controller.mb;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.com.tremn.crm.controller.util.ImageStreamUtil;
import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.InterestArea;
import br.com.tremn.crm.model.entity.Profession;
import br.com.tremn.crm.model.service.ContactService;
import br.com.tremn.crm.model.service.InterestAreaService;

/**
 * Controller para UC Gerenciar Contato
 * @author Solkam
 * @since 01 FEV 2015
 */
@ManagedBean(name="contactMB")
@ViewScoped
public class ContactMB implements Serializable {
	
	@EJB ContactService contactService;
	
	@EJB InterestAreaService interestAreaService;

	
	private ImageStreamUtil imageStreamUtil = new ImageStreamUtil();
	
	private List<Contact> contacts;
	
	private Contact contact;
	

	//filtros
	private String filterName;
	private String filterEmail;
	private String filterCity;
	

	//inits..
	@PostConstruct void init() {
		search();
		populateComboInterestAreas();
	}
	

	private void populateContacts() {
		contacts = contactService.searchContactByFilters(filterName, filterEmail, filterCity);
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
		//validacoes
		contact.validateBirthdate();
		contact.validateDocuments();
		//salva
		contactService.saveContact(contact);
		//prepara exibicao
		populateContacts();
		refresh();
		JSFUtil.addInfoMessage("Contato salvo com sucesso");
	}

	
	public void remove() {
		contactService.removeContact(contact);
		populateContacts();
		JSFUtil.addInfoMessage("Contato removido");
	}
	
	
	//areas de interesse
	private List<InterestArea> comboInterestAreas;
	
	private void populateComboInterestAreas() {
		comboInterestAreas = interestAreaService.searchActiveInterestArea();
	}
	
	
	public void saveInterestAresOfContact() {
		contact = contactService.saveContact(contact);
		refresh();
		JSFUtil.addInfoMessage("Áreas de Interesse salvas com sucesso");
	}
	
	
	//util
	private void refresh() {
		contact = contactService.refreshContact(contact); 
		
		//area de interesse (para evitar LIE)
		List<InterestArea> areas = new ArrayList<>();
		areas.addAll( contact.getInterestAreas() );
		contact.setInterestAreas( areas );
		
		//profissoes (para evitar LIE)
		List<Profession> professions = new ArrayList<>();
		professions.addAll( contact.getProfessions() );
		contact.setProfessions( professions );
	}

	
	//autocomplete
	public List<Contact> completeContact(String fragment) {
		List<Contact> contacts = contactService.searchContactByFistNameOrLastNameOrCity(fragment, fragment);
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
	 * redimension�-la e gravar no disco.
	 * @param event
	 * @throws IOException
	 */
	private void redimImage(FileUploadEvent event) throws IOException {
		//1.extens�o da imagem
		String imageExtension = imageStreamUtil.extractExtension( event.getFile().getFileName() );
		contact.setImageExtension(imageExtension);

		//2.conteudo bin�rio da imagem
		InputStream imageInputStream = event.getFile().getInputstream();
		byte[] imageBinary = imageStreamUtil.getBinaryDimensionated(imageInputStream, imageExtension);
		contact.setImageBinary( imageBinary );
	}
	
	
	/**
	 * Grava no disco a imagem do contact.
	 * Se ele n�o tiver imagem, n�o grava nada.
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
	 * Salva o contact que implicitamente salvar� sua imagem
	 */
	public void saveImageInDB() {
		contact = contactService.saveContact( contact );
		refresh();
		populateContacts();
		JSFUtil.addInfoMessage("Foto salva com sucesso");
	}
	
	
	/**
	 * Remove as informa�oes da imagem
	 * Nota: fisicamente a imagem continua no FileSystem
	 */
	public void removeImage() {
		contact.setImageBinary( null );
		contact.setImageExtension( null );
		contact = contactService.saveContact( contact );
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
	public List<InterestArea> getComboInterestAreas() {
		return comboInterestAreas;
	}
	
}
