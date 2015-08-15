package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.InterestArea;
import br.com.tremn.crm.model.entity.Profession;
import br.com.tremn.crm.model.entity.enumeration.Gender;
import br.com.tremn.crm.model.service.InterestAreaService;
import br.com.tremn.crm.model.service.ProfessionService;
import br.com.tremn.crm.model.service.ReportService;

@ManagedBean(name="rContactMB")
@ViewScoped
public class ReportContactMB implements Serializable {
	
	@EJB ReportService reportService;
	
	@EJB InterestAreaService interestAreaService;
	@EJB ProfessionService professionService;
	
	//combo
	private List<InterestArea> comboInterestAreas;
	private List<Profession> comboProfessions;
	
	
	//filtros
	private Integer filterBirthDay;
	private Integer filterBirthMonth;
	private Integer filterBirthYear;
	private Gender filterGender;
	private String filterCity;
	private String filterState;
	private List<InterestArea> filterInterestAreas;
	private List<Profession> filterProfessions;
	
	
	//resultado
	private List<Contact> contacts;
	
	
	@PostConstruct
	void init() {
		populateComboInterestAreas();
		populateComboProfessions();
	}
	
	
	private void populateComboInterestAreas() {
		comboInterestAreas = interestAreaService.searchActiveInterestArea();
	}
	
	private void populateComboProfessions() {
		comboProfessions = professionService.searchActiveProfession();
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
													   );
		JSFUtil.addMessageAboutResult(contacts);
	}



	
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
	
	
	
}
