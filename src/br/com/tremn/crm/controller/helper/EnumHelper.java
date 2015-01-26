package br.com.tremn.crm.controller.helper;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.tremn.crm.model.entity.enumeration.Profile;

@ManagedBean(name="enumHelper")
@ApplicationScoped
public class EnumHelper {
	
	public Profile[] getProfiles() {
		return Profile.values();
	}

}
