package br.com.tremn.crm.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.tremn.crm.model.entity.Address;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.InterestArea;
import br.com.tremn.crm.model.entity.Profession;
import br.com.tremn.crm.model.entity.enumeration.Gender;
import static br.com.tremn.crm.model.util.QueryUtil.*;


/**
 * Serviços de Relatorios
 * @author Solkam
 * @since 15 AGO 2015
 */
@Stateless
public class ReportService {
	
	@PersistenceContext
	private EntityManager manager;

	/**
	 * Pesquisa contatos para o relatorio de contatos
	 * @param filterBirthDay
	 * @param filterBirthMonth
	 * @param filterBirthYear
	 * @param filterGender
	 * @return
	 */
	public List<Contact> searchContactByFilters( Integer filterBirthDay
											   , Integer filterBirthMonth
											   , Integer filterBirthYear
											   , Gender filterGender
											   , String filterCity
											   , String filterState
											   , List<InterestArea> filterInterestAreas
											   , List<Profession> filterProfessions) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
		Root<Contact> root = criteria.from(Contact.class);
		
		Predicate conjunction = builder.conjunction();
		//dia nascimento
		if (isNotNegative(filterBirthDay)) {
			conjunction = builder.and(conjunction
					,builder.equal( root.<Integer>get("birthDay") , filterBirthDay)
					);
		}
		//mes nascimento
		if (isNotNegative(filterBirthMonth)) {
			conjunction = builder.and(conjunction
					,builder.equal( root.<Integer>get("birthMonth") , filterBirthMonth)
					);
		}
		//ano nascimento
		if (isNotNegative(filterBirthYear)) {
			conjunction = builder.and(conjunction
					,builder.equal( root.<Integer>get("birthYear") , filterBirthYear)
					);
		}
		//sexo
		if (isNotNull(filterGender)) {
			conjunction = builder.and(conjunction
					,builder.equal( root.<Gender>get("gender") , filterGender)
					);
		}
		//cidade
		if (isNotBlank(filterCity)) {
			conjunction = builder.and(conjunction
					,builder.like( root.<Address>get("address").<String>get("addressCity") , toLikeMatchModeANY(filterCity) )
					);
		}
		//estado
		if (isNotBlank(filterState)) {
			conjunction = builder.and(conjunction
					,builder.like( root.<Address>get("address").<String>get("addressState") , toLikeMatchModeANY(filterState) )
					);
		}
		//areas de interesse
		if (isNotEmpty(filterInterestAreas)) {
			Join<Contact, InterestArea> joinInterestArea = root.<Contact,InterestArea>join("interestAreas");
			conjunction = builder.and(conjunction
					,joinInterestArea.in( filterInterestAreas )
					);
		}
		//professions
		if (isNotEmpty(filterProfessions)) {
			Join<Contact, Profession> joinProfession = root.<Contact,Profession>join("professions");
			conjunction = builder.and(conjunction
					,joinProfession.in( filterProfessions )
					);
		}
		
		//where e order by
		criteria.where( conjunction );
		
		criteria.orderBy( builder.asc(root.get("firstName"))
				        , builder.asc(root.get("lastName"))
				);
		
		return manager.createQuery(criteria).getResultList();
	}
	
	

}
