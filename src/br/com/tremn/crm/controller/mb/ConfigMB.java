package br.com.tremn.crm.controller.mb;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.tremn.crm.controller.util.ImageStreamUtil;
import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.service.ContactService;

/**
 * Configurador para diversoes ações RNF:
 * - gravar imagens de contatos em disco para serem acessadas via navegador
 * 
 * @author Solkam
 * @since 03 MAR 2015
 */
@ManagedBean(name="configMB")
public class ConfigMB {
	
	@EJB ContactService contactService;
	
	public String storeContactImagesOnFS() throws IOException {
		ImageStreamUtil util = new ImageStreamUtil();
		
		List<Contact> contacts = contactService.searchContactWithImage();
		for (Contact contact : contacts) {
			util.writeInFileSystem(contact.getImageBinary(), contact.getImageName() );
		}
		JSFUtil.addInfoMessage("Imagens dos Contatos salvas em disco");
		return "home";
	}
	

}
