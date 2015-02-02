package br.com.tremn.crm.controller.helper;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.tremn.crm.model.entity.enumeration.Gender;
import br.com.tremn.crm.model.entity.enumeration.Profile;

/**
 * Helper para enum serem visualizados em componentes Select
 * @author Solkam
 * @since 25 JAN 2015
 */
@ManagedBean(name="enumHelper")
@ApplicationScoped
public class EnumHelper {
	
	
	public Profile[] getProfiles() {
		return Profile.values();
	}
	
	
	public Gender[] getGenders() {
		return Gender.values();
	}

}
