package br.com.tremn.crm.model.entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

/**
 * Classe embarcável com os contatos de redes sociais
 * @author Solkam
 * @since 01 MAR 2014
 */
@Embeddable
public class SocialNetwork {
	
	@Size(max=100)
	private String socialFacebook;
	
	@Size(max=100)
	private String socialTwitter;
	
	@Size(max=100)
	private String socialLinkedIn;
	
	@Size(max=100)
	private String socialGooglePlus;

	
	
	public String getSocialFacebook() {
		return socialFacebook;
	}

	public void setSocialFacebook(String socialFacebook) {
		this.socialFacebook = socialFacebook;
	}

	public String getSocialTwitter() {
		return socialTwitter;
	}

	public void setSocialTwitter(String socialTwitter) {
		this.socialTwitter = socialTwitter;
	}

	public String getSocialLinkedIn() {
		return socialLinkedIn;
	}

	public void setSocialLinkedIn(String socialLinkedIn) {
		this.socialLinkedIn = socialLinkedIn;
	}

	public String getSocialGooglePlus() {
		return socialGooglePlus;
	}

	public void setSocialGooglePlus(String socialGooglePlus) {
		this.socialGooglePlus = socialGooglePlus;
	}

}
