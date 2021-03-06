package br.com.tremn.crm.controller.mb;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.com.tremn.crm.controller.mb.security.SessionHolder;
import br.com.tremn.crm.controller.util.ImageStreamUtil;
import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.ContactBusinessCard;
import br.com.tremn.crm.model.entity.ContactInscriptionForm;
import br.com.tremn.crm.model.entity.ContactObservation;
import br.com.tremn.crm.model.entity.InterestArea;
import br.com.tremn.crm.model.entity.Profession;
import br.com.tremn.crm.model.service.ContactService;
import br.com.tremn.crm.model.service.InterestAreaService;
import br.com.tremn.crm.model.service.ProfessionService;

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
	
	@EJB ProfessionService professionService;
	
	
	@ManagedProperty("#{sessionHolder}")
	private SessionHolder sessionHolder;

	
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
		populateComboProfessions();
		resetObservation();
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
		contact = contactService.saveContact(contact);
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
	
	
	//profissoes
	private List<Profession> comboProfessions;

	private void populateComboProfessions() {
		comboProfessions = professionService.searchActiveProfession();
	}
	
	public void saveProfessionsOfContact() {
		contact = contactService.saveContact(contact);
		refresh();
		JSFUtil.addInfoMessage("Profissões salvas com sucesso");
	}
	
	
	//observacoes
	private ContactObservation newObservation;
	
	private void resetObservation() {
		newObservation = new ContactObservation();
		newObservation.setResponsable( sessionHolder.getUser().getEmail() );
	}
	
	public void saveObservation() {
		newObservation.setContact(contact);
		newObservation.setObservationDate( new Date() );
		contactService.saveContactObservation(newObservation);
		resetObservation();
		refresh();
		JSFUtil.addInfoMessage("Observação salva com sucesso");
	}
	
	public void removeObservation(ContactObservation selectedObs) {
		contactService.removeContactObservation(selectedObs);
		refresh();
		JSFUtil.addInfoMessage("Observação removida");
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
	 * redimensiona-la e gravar no disco.
	 * @param event
	 * @throws IOException
	 */
	private void redimImage(FileUploadEvent event) throws IOException {
		//1.extensao da imagem
		String imageExtension = imageStreamUtil.extractExtension( event.getFile().getFileName() );
		contact.setImageExtension(imageExtension);

		//2.conteudo binario da imagem
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
	 * Salva o contact que implicitamente salvara sua imagem
	 */
	public void saveImageInDB() {
		contact = contactService.saveContact( contact );
		refresh();
		populateContacts();
		JSFUtil.addInfoMessage("Foto salva com sucesso");
	}
	
	
	/**
	 * Remove as informacoes da imagem
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
	
	
	
	/* *****************
	 * Cartão de Negocio
	 *******************/
	public void onBusinessCardUpload(FileUploadEvent event) throws IOException {
		ContactBusinessCard newBusinessCard = createBusinessCard();
		
		redimBusinessCard(event, newBusinessCard);
		newBusinessCard = saveBusinessCard(newBusinessCard);
		writeBusinessCardImage(newBusinessCard);
		
		JSFUtil.addInfoMessage("Cartão de Negócio salvo com sucesso");
	}

	
	private ContactBusinessCard createBusinessCard() {
		ContactBusinessCard businessCard = new ContactBusinessCard();
		businessCard.setContact( contact );
		return businessCard;
	}

	private void redimBusinessCard(FileUploadEvent event, ContactBusinessCard newBusinessCard) throws IOException {
		//1.extensao da imagem
		String imageExtension = imageStreamUtil.extractExtension( event.getFile().getFileName() );
		newBusinessCard.setImageExtension(imageExtension);
		//2.conteudo binario da imagem
		InputStream imageInputStream = event.getFile().getInputstream();
		int wDim = ContactBusinessCard.W_DIM;
		int hDim = ContactBusinessCard.H_DIM;
		byte[] imageBinary = imageStreamUtil.getBinaryDimensionated(imageInputStream, imageExtension, wDim, hDim);
		newBusinessCard.setImageBinary(imageBinary);
	}
	
	private void writeBusinessCardImage(ContactBusinessCard businessCard) throws IOException {
		byte[] imageBinary = businessCard.getImageBinary();
		String imageName = businessCard.getImageName();
		imageStreamUtil.writeInFileSystem(imageBinary, imageName);
	}
	
	
	private ContactBusinessCard saveBusinessCard(ContactBusinessCard newBusinessCard) {
		newBusinessCard = contactService.saveContactBusinessCard(newBusinessCard);
		refresh();
		return newBusinessCard;
	}
	
	
	public void removeBusinessCard(ContactBusinessCard card) {
		contactService.removeContactBusinessCard(card);
		refresh();
		JSFUtil.addInfoMessage("Cartão de Negócio removido");
	}
	
	
	
	/* ****************
	 * Fichas Inscrição  
	 ******************/
	public void onInscriptionFormUpload(FileUploadEvent event) throws IOException {
		ContactInscriptionForm newInscriptionForm = createInscriptionForm();
		
		redimInscriptionForm(event, newInscriptionForm);
		newInscriptionForm = saveInscriptionForm(newInscriptionForm);
		writeInscriptionFormImage(newInscriptionForm);
		
		JSFUtil.addInfoMessage("Formulário de Inscrição salvo com sucesso");
	}
	
	private ContactInscriptionForm createInscriptionForm() {
		ContactInscriptionForm form = new ContactInscriptionForm();
		form.setContact( contact );
		return form;
	}
	
	private void redimInscriptionForm(FileUploadEvent event, ContactInscriptionForm newInscriptionForm) throws IOException {
		//1.extensao da imagem
		String imageExtension = imageStreamUtil.extractExtension( event.getFile().getFileName() );
		newInscriptionForm.setImageExtension(imageExtension);
		//2.conteudo binario da imagem
		InputStream imageInputStream = event.getFile().getInputstream();
		int wDim = ContactInscriptionForm.W_DIM;
		int hDim = ContactInscriptionForm.H_DIM;
		byte[] imageBinary = imageStreamUtil.getBinaryDimensionated(imageInputStream, imageExtension, wDim, hDim);
		newInscriptionForm.setImageBinary(imageBinary);
	}

	private ContactInscriptionForm saveInscriptionForm(ContactInscriptionForm newInscriptionForm) {
		newInscriptionForm = contactService.saveContactInscriptionForm(newInscriptionForm);
		refresh();
		return newInscriptionForm;
	}
	
	private void writeInscriptionFormImage(ContactInscriptionForm newInscriptionForm) throws IOException {
		byte[] imageBinary = newInscriptionForm.getImageBinary();
		String imageName = newInscriptionForm.getImageName();
		imageStreamUtil.writeInFileSystem(imageBinary, imageName);
	}
	
	
	public void removeInscriptionForm(ContactInscriptionForm selectedForm) {
		contactService.removeContactInscriptionForm(selectedForm);
		refresh();
		JSFUtil.addInfoMessage("Ficha de inscrição removida");
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
	public List<Profession> getComboProfessions() {
		return comboProfessions;
	}
	public ContactObservation getNewObservation() {
		return newObservation;
	}
	public void setNewObservation(ContactObservation newObservation) {
		this.newObservation = newObservation;
	}
	public void setSessionHolder(SessionHolder sessionHolder) {
		this.sessionHolder = sessionHolder;
	}
	
}
