package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;







import org.primefaces.context.RequestContext;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.UserTremn;
import br.com.tremn.crm.model.entity.enumeration.Profile;
import br.com.tremn.crm.model.service.UserService;

/**
 * Controller para UC Gerenciar Usuários
 * @author Solkam
 * @since 26 JAN 2015
 */
@ManagedBean(name="userBean")
@ViewScoped
public class UserBean implements Serializable {
	
	@EJB UserService service;
	
	private UserTremn user;
	
	private List<UserTremn> users;
	
	//filtros
	private String filterName;
	private String filterEmail;
	private Profile filterProfile;
	
	
	@PostConstruct void init() {
		search();
	}
	
	
	public void search() {
		users = service.searchUserTremnByFilters();
	}
	
	public void reset() {
		user = new UserTremn();
		RequestContext.getCurrentInstance().openDialog("DialogUser");
	}
	
	public void manage(UserTremn selectedUser) {
		this.user = selectedUser;
		RequestContext.getCurrentInstance().openDialog("DialogUser");
	}
	
	public void save() {
		user = service.saveUserTremn(user);
		JSFUtil.addInfoMessage("Usuário salvo com sucesso");
		
	}
	public void remove() {
		service.removeUserTremn(user);
		JSFUtil.addInfoMessage("Usuário removido");
	}

	
	//acessores...
	private static final long serialVersionUID = 2577284491525285700L;
	public UserTremn getUser() {
		return user;
	}
	public void setUser(UserTremn user) {
		this.user = user;
	}
	public List<UserTremn> getUsers() {
		return users;
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


	public Profile getFilterProfile() {
		return filterProfile;
	}


	public void setFilterProfile(Profile filterProfile) {
		this.filterProfile = filterProfile;
	}
	
}
