package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Profession;
import br.com.tremn.crm.model.service.ProfessionService;

/**
 * Controller para profissoes
 * @author Solkam
 * @since 27 JUL 2015
 */
@ManagedBean(name="professionMB")
@ViewScoped
public class ProfessionMB implements Serializable {

	@EJB ProfessionService service;
	
	private List<Profession> professions;
	
	private Profession newProfession;
	
	
	@PostConstruct void init() {
		reset();
		search();
	}
	
	
	private void populateProfessions() {
		professions = service.searchProfession();
	}
	
	
	
	//actions
	
	public void search() {
		populateProfessions();
		JSFUtil.addMessageAboutResult(professions);
	}
	
	private void reset() {
		newProfession = new Profession();
	}
	
	public void save() {
		service.saveProfession(newProfession);
		populateProfessions();
		reset();
		JSFUtil.addInfoMessage("Profissão salva com sucesso");
	}
	
	public void saveAll() {
		for (Profession professionVar : professions) {
			service.saveProfession(professionVar);
		}
		populateProfessions();
		JSFUtil.addInfoMessage("Todas as profissões salvas com sucesso");
	}
	
	public void remove(Profession selectedProfession) {
		service.removeProfession(selectedProfession);
		populateProfessions();
		JSFUtil.addInfoMessage(String.format("Profissão [%s] removida", selectedProfession.getName() ));
	}




	//acessores...
	private static final long serialVersionUID = -347132769129023056L;

	public Profession getNewProfession() {
		return newProfession;
	}
	public void setNewProfession(Profession newProfession) {
		this.newProfession = newProfession;
	}
	public List<Profession> getProfessions() {
		return professions;
	}
	
	
}
