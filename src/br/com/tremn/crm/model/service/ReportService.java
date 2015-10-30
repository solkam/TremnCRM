package br.com.tremn.crm.model.service;

import static br.com.tremn.crm.model.util.QueryUtil.isNotBlank;
import static br.com.tremn.crm.model.util.QueryUtil.isNotEmpty;
import static br.com.tremn.crm.model.util.QueryUtil.isNotNegative;
import static br.com.tremn.crm.model.util.QueryUtil.isNotNull;
import static br.com.tremn.crm.model.util.QueryUtil.toLikeMatchModeANY;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import br.com.tremn.crm.model.entity.Address;
import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.Event;
import br.com.tremn.crm.model.entity.InterestArea;
import br.com.tremn.crm.model.entity.Maturity;
import br.com.tremn.crm.model.entity.PaymentMethod;
import br.com.tremn.crm.model.entity.Product;
import br.com.tremn.crm.model.entity.Profession;
import br.com.tremn.crm.model.entity.Vinculo;
import br.com.tremn.crm.model.entity.enumeration.EventStatus;
import br.com.tremn.crm.model.entity.enumeration.Gender;
import br.com.tremn.crm.model.entity.enumeration.ParticipationCategory;
import br.com.tremn.crm.model.entity.enumeration.VinculoType;


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
	 * Pesquisa eventos pelos filtros usando criteria
	 * @param filterYear
	 * @param filterMonths
	 * @param filterProducts
	 * @param filterEventStatusList
	 * @param filterPaymentMethods
	 * @return
	 */
	public List<Event> searchEventByFilter(Integer filterYear
										  ,List<Integer> filterMonths
										  ,List<Product> filterProducts
										  ,List<EventStatus> filterEventStatusList
										  ,List<PaymentMethod> filterPaymentMethods
										  ) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
		Root<Event> rootEvent = criteria.from(Event.class);
		Predicate conjunction = builder.conjunction();
		//ano
		if (isNotNegative(filterYear)) {
			conjunction = builder.and(conjunction,
					builder.equal(  
						 builder.function("YEAR", Integer.class, rootEvent.<Date>get("beginDate"))
						,filterYear
						)
					);
		}
		//meses
		if (isNotEmpty(filterMonths)) {
			conjunction = builder.and(conjunction,
					builder.function("MONTH", Integer.class, rootEvent.<Date>get("beginDate")).in( filterMonths )
					);
		}
		//produtos
		if (isNotEmpty(filterProducts)) {
			conjunction = builder.and(conjunction, 
					rootEvent.<Product>get("product").in(filterProducts)
					);
		}
		//status
		if (isNotEmpty(filterEventStatusList)) {
			conjunction = builder.and(conjunction,
					rootEvent.<EventStatus>get("status").in(filterEventStatusList)
					);
		}
		//formas de pagamento
		if (isNotEmpty(filterPaymentMethods)) {
			Join<Event, PaymentMethod> joinPaymentMethod = rootEvent.<Event, PaymentMethod>join("possiblePaymentMethods");
			
			conjunction = builder.and(conjunction, 
					joinPaymentMethod.in(filterPaymentMethods) 
					);
		}
		
		criteria.distinct(true);
		
		criteria.where( conjunction );
		
		criteria.orderBy( builder.asc(rootEvent.<Date>get("beginDate")) );
		
		List<Event> events = manager.createQuery(criteria).getResultList();
		
		//eager payment methods
		for (Event eventVar : events) {
			eventVar.getPossiblePaymentMethods().size();
		}
		
		return events;
	}
	
	
	
	
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
											   , List<Profession> filterProfessions
										       , List<Event> filterEventsVinculated
										       , List<VinculoType> filterVinculoTypes
										       , List<Event> filterEventsNotVinculated
										       , List<ParticipationCategory> filterParticipantCategories
										       , Contact filterContactWhoIndicated
										       , List<PaymentMethod> filterPaymentMethods
										       , List<Maturity> filterMaturities
				                               ) {
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
		//eventos vinculados
		if (isNotEmpty(filterEventsVinculated)) {
			
			Subquery<Vinculo> subqueryVinculo = criteria.subquery(Vinculo.class);
			Root<Vinculo> rootVinculo = subqueryVinculo.from(Vinculo.class);
			subqueryVinculo.select(rootVinculo);
			
			Predicate conjunctionAux = builder.conjunction();
			conjunctionAux = builder.and(conjunctionAux
					, rootVinculo.<Event>get("event").in( filterEventsVinculated )
					);
			conjunctionAux = builder.and(conjunctionAux
					, builder.equal( rootVinculo.<Contact>get("contact"), root )
					);
			//tipos de vinculos
			if (isNotEmpty(filterVinculoTypes)) {
				conjunctionAux = builder.and(conjunctionAux
						, rootVinculo.<VinculoType>get("type").in( filterVinculoTypes )
						);
			}
			//forma de pagamento
			if (isNotEmpty(filterPaymentMethods)) {
				conjunctionAux = builder.and(conjunctionAux
						, rootVinculo.<PaymentMethod>get("paymentMethod").in( filterPaymentMethods )
						);
			}
			
			
			subqueryVinculo.where(conjunctionAux);
			
			conjunction = builder.and(conjunction
					, builder.exists(subqueryVinculo)
					);
		}
		//eventos NÂO vinculados
		if (isNotEmpty(filterEventsNotVinculated)) {
			Subquery<Vinculo> subqueryVinculo = criteria.subquery(Vinculo.class);
			Root<Vinculo> rootVinculo = subqueryVinculo.from(Vinculo.class);
			subqueryVinculo.select(rootVinculo);
			
			Predicate conjunctionAux = builder.conjunction();
			conjunctionAux = builder.and(conjunctionAux
					, rootVinculo.<Event>get("event").in( filterEventsNotVinculated )
					);
			conjunctionAux = builder.and(conjunctionAux
					, builder.equal( rootVinculo.<Contact>get("contact"), root )
					);
			
			subqueryVinculo.where(conjunctionAux);
			
			conjunction = builder.and(conjunction
					, builder.not( builder.exists(subqueryVinculo) )
					);
		}
		//categorias de participação
		if (isNotEmpty(filterParticipantCategories)) {
			conjunction = builder.and(conjunction
					,root.<ParticipationCategory>get("participationCategory").in( filterParticipantCategories )
					);
		}
		//indicado por
		if (isNotNull(filterContactWhoIndicated)) {
			conjunction = builder.and(conjunction
					,builder.equal( root.<Contact>get("contactWhoIndicated") , filterContactWhoIndicated)
					);
		}
		//maturidades
		if (isNotEmpty(filterMaturities)) {
			conjunction = builder.and(conjunction
					,root.<Maturity>get("maturity").in( filterMaturities )
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
