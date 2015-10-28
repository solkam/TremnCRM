package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.Event;
import br.com.tremn.crm.model.entity.InterestArea;
import br.com.tremn.crm.model.entity.Maturity;
import br.com.tremn.crm.model.entity.PaymentMethod;
import br.com.tremn.crm.model.entity.Profession;
import br.com.tremn.crm.model.entity.enumeration.Gender;
import br.com.tremn.crm.model.entity.enumeration.ParticipationCategory;
import br.com.tremn.crm.model.entity.enumeration.VinculoType;
import br.com.tremn.crm.model.service.EventService;
import br.com.tremn.crm.model.service.InterestAreaService;
import br.com.tremn.crm.model.service.MaturityService;
import br.com.tremn.crm.model.service.PaymentMethodService;
import br.com.tremn.crm.model.service.ProfessionService;
import br.com.tremn.crm.model.service.ReportService;

@ManagedBean(name="rContactMB")
@ViewScoped
public class ReportContactMB implements Serializable {
	
	@EJB ReportService reportService;
	
	@EJB InterestAreaService interestAreaService;
	@EJB ProfessionService professionService;
	@EJB MaturityService maturityService;
	@EJB EventService eventService;
	@EJB PaymentMethodService paymentMethodService;
	
	//combo
	private List<InterestArea> comboInterestAreas;
	private List<Profession> comboProfessions;
	private List<Event> comboEvents;
	private List<PaymentMethod> comboPaymentMethods;
	private List<Maturity> comboMaturities;
	
	//filtros
	private Integer filterBirthDay;
	private Integer filterBirthMonth;
	private Integer filterBirthYear;
	private Gender filterGender;
	private String filterCity;
	private String filterState;
	private List<InterestArea> filterInterestAreas;
	private List<Profession> filterProfessions;
	private List<Event> filterEventsVinculated;
	private List<Event> filterEventsNotVinculated;
	private List<VinculoType> filterVinculoTypes;
	private List<PaymentMethod> filterPaymentMethods;
	private List<ParticipationCategory> filterParticipantCategories;
	private Contact filterContactWhoIndicated;
	private List<Maturity> filterMaturities;
	
	
	//resultado
	private List<Contact> contacts;
	
	@PostConstruct
	void init() {
		populateComboInterestAreas();
		populateComboProfessions();
		populateComboEvents();
		populateComboPaymentMethods();
		populateComboMaturities();
	}
	
	private void populateComboPaymentMethods() {
		comboPaymentMethods = paymentMethodService.searchPaymentMethod();
	}

	private void populateComboEvents() {
		comboEvents = eventService.searchEvent();
	}

	private void populateComboInterestAreas() {
		comboInterestAreas = interestAreaService.searchActiveInterestArea();
	}
	
	private void populateComboProfessions() {
		comboProfessions = professionService.searchActiveProfession();
	}

	
	private void populateComboMaturities() {
		comboMaturities = maturityService.searchMaturity();
	}
	

	//actions...
	public void search() {
		contacts = reportService.searchContactByFilters( filterBirthDay
													   , filterBirthMonth
													   , filterBirthYear
													   , filterGender
													   , filterCity
													   , filterState
													   , filterInterestAreas
													   , filterProfessions
													   , filterEventsVinculated
													   , filterVinculoTypes
													   , filterEventsNotVinculated
													   , filterParticipantCategories
													   , filterContactWhoIndicated
													   , filterPaymentMethods
													   , filterMaturities
													   );
		JSFUtil.addMessageAboutResult(contacts);
	}


	//helper
//	public String definirMaturityName(Contact contact) {
//		for (Maturity maturityVar : comboMaturities) {
//			if (maturityVar.getFlagInsideAges( contact.getCalculatedAge() )) {
//				return maturityVar.getName();
//			}
//		}
//		return "?";
//	}

	
	//acessores...
	private static final long serialVersionUID = -4564454271538398097L;
	public Integer getFilterBirthDay() {
		return filterBirthDay;
	}
	public void setFilterBirthDay(Integer filterBirthDay) {
		this.filterBirthDay = filterBirthDay;
	}
	public Integer getFilterBirthMonth() {
		return filterBirthMonth;
	}
	public void setFilterBirthMonth(Integer filterBirthMonth) {
		this.filterBirthMonth = filterBirthMonth;
	}
	public Integer getFilterBirthYear() {
		return filterBirthYear;
	}
	public void setFilterBirthYear(Integer filterBirthYear) {
		this.filterBirthYear = filterBirthYear;
	}
	public Gender getFilterGender() {
		return filterGender;
	}
	public void setFilterGender(Gender filterGender) {
		this.filterGender = filterGender;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public String getFilterCity() {
		return filterCity;
	}
	public void setFilterCity(String filterCity) {
		this.filterCity = filterCity;
	}
	public String getFilterState() {
		return filterState;
	}
	public void setFilterState(String filterState) {
		this.filterState = filterState;
	}
	public List<InterestArea> getFilterInterestAreas() {
		return filterInterestAreas;
	}
	public void setFilterInterestAreas(List<InterestArea> filterInterestAreas) {
		this.filterInterestAreas = filterInterestAreas;
	}
	public List<Profession> getFilterProfessions() {
		return filterProfessions;
	}
	public void setFilterProfessions(List<Profession> filterProfessions) {
		this.filterProfessions = filterProfessions;
	}
	public List<InterestArea> getComboInterestAreas() {
		return comboInterestAreas;
	}
	public List<Profession> getComboProfessions() {
		return comboProfessions;
	}
	public List<Event> getComboEvents() {
		return comboEvents;
	}
	public List<Event> getFilterEventsVinculated() {
		return filterEventsVinculated;
	}
	public void setFilterEventsVinculated(List<Event> filterEventsVinculated) {
		this.filterEventsVinculated = filterEventsVinculated;
	}
	public List<Event> getFilterEventsNotVinculated() {
		return filterEventsNotVinculated;
	}
	public void setFilterEventsNotVinculated(List<Event> filterEventsNotVinculated) {
		this.filterEventsNotVinculated = filterEventsNotVinculated;
	}
	public List<VinculoType> getFilterVinculoTypes() {
		return filterVinculoTypes;
	}
	public void setFilterVinculoTypes(List<VinculoType> filterVinculoTypes) {
		this.filterVinculoTypes = filterVinculoTypes;
	}
	public List<ParticipationCategory> getFilterParticipantCategories() {
		return filterParticipantCategories;
	}
	public void setFilterParticipantCategories(
			List<ParticipationCategory> filterParticipantCategories) {
		this.filterParticipantCategories = filterParticipantCategories;
	}
	public Contact getFilterContactWhoIndicated() {
		return filterContactWhoIndicated;
	}
	public void setFilterContactWhoIndicated(Contact filterContactWhoIndicated) {
		this.filterContactWhoIndicated = filterContactWhoIndicated;
	}
	public List<PaymentMethod> getFilterPaymentMethods() {
		return filterPaymentMethods;
	}
	public void setFilterPaymentMethods(List<PaymentMethod> filterPaymentMethods) {
		this.filterPaymentMethods = filterPaymentMethods;
	}
	public List<PaymentMethod> getComboPaymentMethods() {
		return comboPaymentMethods;
	}
	public List<Maturity> getFilterMaturities() {
		return filterMaturities;
	}
	public void setFilterMaturities(List<Maturity> filterMaturities) {
		this.filterMaturities = filterMaturities;
	}
	public List<Maturity> getComboMaturities() {
		return comboMaturities;
	}
	
}
