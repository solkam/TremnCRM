package br.com.tremn.crm.controller.mb.security;

import java.io.Serializable;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.UserTremn;

/**
 * Objeto que guarda infos relevantes na sessao
 * @author Solkam
 * @since 24 AGO 2015
 */
@ManagedBean(name="sessionHolder")
@SessionScoped
public class SessionHolder implements Serializable {
	
	private static final String USER_KEY = "USER";

	private UserTremn user;
	
	
	
	public void initSession(UserTremn user) {
		this.user = user;
		putUserOnSession(user);
	}
	

	public void finalizeSession() {
		putUserOnSession(null);
	}
	
	
	//util
	private void putUserOnSession(UserTremn user) {
		JSFUtil.getHttpSession().setAttribute(USER_KEY, user);
	}
	
	
	//acessores...
	private static final long serialVersionUID = 317919622145173395L;

	public UserTremn getUser() {
		return user;
	}


	public Boolean getFlagLogged() {
		return user!=null;
	}
	
	
	public Boolean getFlagTecnology() {
		if (user==null) {
			return false;
		}
		if (user.getFlagTecnology()==null) {
			return false;
		}
		
		return user.getFlagTecnology();
	}
	

	
}
