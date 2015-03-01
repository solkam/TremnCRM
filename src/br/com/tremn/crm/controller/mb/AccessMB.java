package br.com.tremn.crm.controller.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.UserTremn;
import br.com.tremn.crm.model.exception.InfraException;
import br.com.tremn.crm.model.service.UserService;

/**
 * Controller para UC Realizar Autenticação
 * @author Solkam
 * @since 25 JAN 2015
 */
@ManagedBean(name="accessMB")
@SessionScoped
public class AccessMB implements Serializable {

	private static final String USER_KEY = "USER";

	@EJB UserService userService;

	
	private String email;
	private String pass;
	
	
	
	/**
	 * Invoca a autentica do container. Uma autenticado, recupera o 
	 * objeto usuario da base e coloca no escopo da sessão. 
	 * Finalmente, redireciona para a pagina home.
	 * @return pagina inicial, caso sucesso
	 */
	public String doLogin() {
		if (isAuthenticated()) {
			//busca o object usuário no banco e coloca na session
			UserTremn user = userService.findUserTremnByEmailAndPassword(email, pass);
			putUserOnSession(user);
			return gotoHomePage();
		
		} else {//autenticacao falhou
			JSFUtil.addErroMessage("Email ou senha inválidos");
			return gotoLoginPage();
		}
	}
	
	public void doLogout() {
		try {
			JSFUtil.getHttpServletRequest().logout();
			JSFUtil.getHttpSession().invalidate();
			putUserOnSession( null );
			gotoLoginPage();
			
		} catch (ServletException e) {
			throw new InfraException(e);
		}
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
	private boolean isAuthenticated() {
		try {
			HttpServletRequest request = JSFUtil.getHttpServletRequest();
			request.login(email, pass);
			return true;
			
		} catch (ServletException e) {
			return false;
		}
	}
	
	
	public boolean isLogged() {
		return getUserOnSession()!=null;
	}
	
	

	
	
	
	
	//util
	private void putUserOnSession(UserTremn user) {
		JSFUtil.getHttpSession().setAttribute(USER_KEY, user);
	}
	
	public UserTremn getUserOnSession() {
		return (UserTremn) JSFUtil.getHttpSession().getAttribute(USER_KEY);
	}
	
	
	
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
	
	
	

}
