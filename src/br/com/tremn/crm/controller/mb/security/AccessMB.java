package br.com.tremn.crm.controller.mb.security;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.UserTremn;
import br.com.tremn.crm.model.service.UserService;

/**
 * Controller para Realizar Login e Logout
 * @author Solkam
 * @since 25 JAN 2015
 */
@ManagedBean(name="accessMB")
@RequestScoped
public class AccessMB implements Serializable {

	@EJB UserService userService;

	@ManagedProperty("#{sessionHolder}")
	private SessionHolder sessionHolder;
	
	
	private String email;
	private String pass;
	
	
	
	public String doLogin() {
		UserTremn user = userService.findUserTremnByEmailAndPassword(email, pass);
		if (user!=null) {
			return grantAcess(user);
		} else {
			return denyAcess();
		}
	}


	private String grantAcess(UserTremn user) {
		sessionHolder.initSession(user);
		return gotoHomePage();
	}

	
	private String denyAcess() {
		JSFUtil.addErroMessage("Email ou senha inválidos");
		return gotoLoginPage();
	}
	


	public String doLogout() {
		sessionHolder.finalizeSession();
		JSFUtil.getHttpSession().invalidate();
		return gotoLoginPage();
	}

	/**
	 * De acordo com o perfil do usuário, 
	 * direciona para uma pagina.
	 * @return
	 */
	private String gotoHomePage() {
		return "home";
	}
	
	private String gotoLoginPage() {
		return "login";
	}
	
	/**
	 * Verifica se o container JAAS autentica o
	 * email e senha
	 * @return
	 */
//	private boolean isAuthenticated() {
//		try {
//			HttpServletRequest request = JSFUtil.getHttpServletRequest();
//			request.login(email, pass);
//			return true;
//			
//		} catch (ServletException e) {
//			return false;
//		}
//	}
	
	
	
	

	
	
	
	
	
	
	
	//acessores...
	private static final long serialVersionUID = 1421116987452326810L;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setSessionHolder(SessionHolder sessionHolder) {
		this.sessionHolder = sessionHolder;
	}

}
