package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.InterestArea;
import br.com.tremn.crm.model.service.InterestAreaService;

/**
 * Controller para área de interesse
 * @author Solkam
 * @since 26 JUN 2015
 */
@ManagedBean(name="interestAreaMB")
@ViewScoped
public class InterestAreaMB implements Serializable {

	@EJB InterestAreaService service;
	
	private InterestArea newInterestArea;
	
	private List<InterestArea> interestAreas;

	
	@PostConstruct void init() {
		reset();
		search();
	}
	
	private void populateInterestAreas() {
		interestAreas = service.searchInterestArea();
	}
	
	
	private void reset() {
		newInterestArea = new InterestArea();
	}
	
	
	public void search() {
		populateInterestAreas();
		JSFUtil.addMessageAboutResult(interestAreas);
	}
	
	public void save() {
		service.saveInterestArea(newInterestArea);
		reset();
		populateInterestAreas();
		JSFUtil.addInfoMessage("Área de Interesse salva com sucesso");
	}
	
	public void saveAll() {
		for (InterestArea areaVar : interestAreas) {
			service.saveInterestArea(areaVar);
		}
		populateInterestAreas();
		JSFUtil.addInfoMessage("Todas as áreas de interesse salvas com sucesso");
	}
	
	public void remove(InterestArea areaSelecionada) {
		service.remove(areaSelecionada);
		populateInterestAreas();
		JSFUtil.addInfoMessage(String.format("Área de Interesse [%s] removida", areaSelecionada.getDescription() ));
	}
	
	
	//acessores...
	private static final long serialVersionUID = -4405135060999549248L;
	public InterestArea getNewInterestArea() {
		return newInterestArea;
	}
	public void setNewInterestArea(InterestArea newInterestArea) {
		this.newInterestArea = newInterestArea;
	}
	public List<InterestArea> getInterestAreas() {
		return interestAreas;
	}
	
}
