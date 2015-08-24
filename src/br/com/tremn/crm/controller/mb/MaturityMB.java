package br.com.tremn.crm.controller.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.entity.Maturity;
import br.com.tremn.crm.model.service.MaturityService;

@ManagedBean(name="maturityMB")
@ViewScoped
public class MaturityMB implements Serializable {
	

	@EJB MaturityService service;
	
	private List<Maturity> maturities;
	
	private Maturity newMaturity;
	
	
	@PostConstruct void init() {
		populateMaturities();
		reset();
	}
	
	
	private void populateMaturities() {
		maturities = service.searchMaturity();
	}

	
	public void reset() {
		newMaturity = new Maturity();
	}

	public void search() {
		populateMaturities();
		JSFUtil.addMessageAboutResult(maturities);
	}
	
	
	public void manage(Maturity selectedMaturity) {
		this.newMaturity = selectedMaturity;
	}
	
	
	public void save() {
		newMaturity = service.saveMaturity(newMaturity);
		populateMaturities();
		reset();
		JSFUtil.addInfoMessage("Maturidade salva com sucesso");
	}
	
	public void saveAll() {
		for (Maturity maturityVar : maturities) {
			service.saveMaturity(maturityVar);
		}
		populateMaturities();
		JSFUtil.addInfoMessage("Todas as maturidades salvas com sucesso");
	}
	
	
	public void remove(Maturity selectedMaturity) {
		service.removeMaturity(selectedMaturity);
		populateMaturities();
		JSFUtil.addInfoMessage("Maturidade removida");
	}


	//acessores...
	private static final long serialVersionUID = -547089387978200127L;
	public List<Maturity> getMaturities() {
		return maturities;
	}
	public Maturity getNewMaturity() {
		return newMaturity;
	}
	public void setNewMaturity(Maturity newMaturity) {
		this.newMaturity = newMaturity;
	}
}
