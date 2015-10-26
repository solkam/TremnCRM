package br.com.tremn.crm.controller.mb;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.tremn.crm.controller.util.JSFUtil;
import br.com.tremn.crm.model.dto.ImportContactDTO;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.Telephone;
import br.com.tremn.crm.model.entity.enumeration.ParticipationCategory;
import br.com.tremn.crm.model.service.ContactService;


/**
 * Controller para Importar Contatos
 * @author Solkam
 * @since 14 SET 2015
 */
@ManagedBean(name="importContactMB")
@ViewScoped
public class ImportContactMB implements Serializable {
	
	@EJB ContactService contactService;
	

	private List<ImportContactDTO> dtos;
	
	private List<ImportContactDTO> selectedDtos;
	
	public void onFileUpload(FileUploadEvent event) throws IOException {
		InputStream inputstream = event.getFile().getInputstream();
		List<String> lines = IOUtils.readLines(inputstream);
		
		dtos = new ArrayList<ImportContactDTO>();
		for (String lineVar : lines) {
			if (lineVar!=null && !lineVar.trim().isEmpty()) {
				String[] cols = lineVar.split(",");
				
				ImportContactDTO dto = new ImportContactDTO();
				dto.setEmail(       removerAspas(cols[0]) );
				dto.setFirstName(   removerAspas(cols[1]) );
				dto.setLastName(    removerAspas(cols[2]) );
				dto.setTelephone(   removerAspas(cols[3]) );
				dto.setMesProducao( removerAspas(cols[4]) );
				dto.setAnoProducao( removerAspas(cols[5]) );
				dto.setRegistroOriginal( lineVar );
				dto.setKey( lineVar.hashCode() );
				dtos.add( dto );
			}
		}
		JSFUtil.addInfoMessage("Contatos foram carregados com sucesso. É necessário agora confirmá-los.");
	}
	
	private String removerAspas(String txt) {
		if (txt==null) {
			return null; 
		} else {
			return txt.replaceAll("\"", "");
		}
	}
	

	public void confirm() {
		for (ImportContactDTO dtoVar : selectedDtos) {
			Contact c = new Contact();
			c.setEmailPrincipal( dtoVar.getEmail() );
			c.setFirstName( dtoVar.getFirstName() );
			c.setLastName( dtoVar.getLastName() );
			
			Telephone tel = new Telephone();
			tel.setTelephoneResidential( dtoVar.getTelephone() );
			c.setTelephone( tel );
			
			//fake
			c.setBirthDay( 01 );
			c.setBirthMonth( 01 );
			c.setBirthYear( 2015 );
			
			c.setParticipationCategory( ParticipationCategory.CLIENT );
			c.setImportDate( new Date() );
			
			String obs = String.format("Producao: %s / %s", dtoVar.getMesProducao(), dtoVar.getAnoProducao() );
//			c.setObservation( obs );//nao existe mais uma unica obs sobre contato
			
			contactService.saveContactWithoutVerify( c );
		}
		String msg = String.format( "Foram confirmados %d contatos importados", selectedDtos.size() );
		JSFUtil.addInfoMessage(msg);
	}
	
	
	//acessores...
	private static final long serialVersionUID = -3239230239992111074L;
	public List<ImportContactDTO> getDtos() {
		return dtos;
	}

	public List<ImportContactDTO> getSelectedDtos() {
		return selectedDtos;
	}

	public void setSelectedDtos(List<ImportContactDTO> selectedDtos) {
		this.selectedDtos = selectedDtos;
	}
	

}
