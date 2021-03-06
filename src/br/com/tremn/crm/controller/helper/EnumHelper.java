package br.com.tremn.crm.controller.helper;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.tremn.crm.model.entity.enumeration.AddressType;
import br.com.tremn.crm.model.entity.enumeration.DateDay;
import br.com.tremn.crm.model.entity.enumeration.DateMonth;
import br.com.tremn.crm.model.entity.enumeration.EventStatus;
import br.com.tremn.crm.model.entity.enumeration.EventType;
import br.com.tremn.crm.model.entity.enumeration.Gender;
import br.com.tremn.crm.model.entity.enumeration.ParticipationCategory;
import br.com.tremn.crm.model.entity.enumeration.ProductCategory;
import br.com.tremn.crm.model.entity.enumeration.Profile;
import br.com.tremn.crm.model.entity.enumeration.TelephoneMobileCompany;
import br.com.tremn.crm.model.entity.enumeration.VinculoType;

/**
 * Helper para enum serem visualizados em componentes Select
 * @author Solkam
 * @since 25 JAN 2015
 */
@ManagedBean(name="enumHelper")
@ApplicationScoped
public class EnumHelper {
	
	
	public AddressType[] getAddressTypes() {
		return AddressType.values();
	}
	
	
	public Profile[] getProfiles() {
		return Profile.values();
	}
	
	
	public Gender[] getGenders() {
		return Gender.values();
	}
	
	
	public DateDay[] getDays() {
		return DateDay.values();
	}
	
	public DateMonth[] getMonths() {
		return DateMonth.values();
	}

	public TelephoneMobileCompany[] getTelephoneMobileCompanies() {
		return TelephoneMobileCompany.values();
	}
	
	public ParticipationCategory[] getParticipationCategories() {
		return ParticipationCategory.values();
	}
	
	public EventStatus[] getEventStatuses() {
		return EventStatus.values();
	}
	
	public ProductCategory[] getProductCategories() {
		return ProductCategory.values();
	}
	
	public EventType[] getEventTypes() {
		return EventType.values();
	}
	
	public VinculoType[] getVinculoTypes() {
		return VinculoType.values();
	}
	
}
