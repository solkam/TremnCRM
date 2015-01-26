package br.com.tremn.crm.controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Utilitário para JSF como faces messages.
 * @author Solkam
 * @since 25 JAN 2015
 */
public class JSFUtil {
	
	/**
	 * Adiciona messagem de erro ao contexto faces
	 * @param txt
	 */
	public static void addErroMessage(String txt) {
		FacesMessage fm = new FacesMessage( txt );
		fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}


	/**
	 * Adiciona mensagem informativa ao contexto face
	 * @param txt
	 */
	public static void addInfoMessage(String txt) {
		FacesMessage fm = new FacesMessage( txt );
		fm.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	
	/**
	 * Recupera o request a partir do contexto do faces
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	/**
	 * Recupera o session a partir do contexto do faces
	 * @return
	 */
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	
	
}
