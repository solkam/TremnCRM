package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.UserTremn;
import br.com.tremn.crm.model.entity.enumeration.Profile;
import br.com.tremn.crm.model.exception.BusinessException;
import br.com.tremn.crm.model.service.UserService;

/**
 * Controller para UC Gerenciar Usuários
 * @author Solkam
 * @since 26 JAN 2015
 */
@ManagedBean(name="userMB")
@ViewScoped
public class UserMB implements Serializable {
	
	@EJB UserService service;
	
	private UserTremn user;
	
	private List<UserTremn> users;
	
	//filtros
	private String filterEmail;
	private Profile filterProfile = Profile.ADM;
	
	//senhas
	private String pass1;
	private String pass2;
	
	
	@PostConstruct void init() {
		search();
	}
	
	private void populateUsers() {
		users = service.searchUserTremnByFilters(filterEmail, filterProfile);
	}
	
	public void search() {
		populateUsers();
		JSFUtil.addMessageAboutResult(users);
	}
	
	public void reset() {
		user = new UserTremn();
	}
	
	public void manage(UserTremn selectedUser) {
		this.user = selectedUser;
	}
	
	public void save() {
		user = service.saveUserTremn(user);
		populateUsers();
		JSFUtil.addInfoMessage("Usuário salvo com sucesso");
		
	}
	
	public void remove() {
		service.removeUserTremn(user);
		populateUsers();
		JSFUtil.addInfoMessage("Usuário removido");
	}
	
	public void savePassword() {
		validatePasswords();
		user.setPassword( pass1 );
		service.saveUserTremn(user);
		JSFUtil.addInfoMessage("Senha salva com sucesso");
	}
	
	
	//util
	private void validatePasswords() {
		if (!pass1.equals(pass2)) {
			throw new BusinessException("Senhas não conferem");
		}
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

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	
}
