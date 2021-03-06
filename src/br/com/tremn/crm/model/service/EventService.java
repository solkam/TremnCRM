package br.com.tremn.crm.model.service;

import static br.com.tremn.crm.model.util.QueryUtil.isNotBlank;
import static br.com.tremn.crm.model.util.QueryUtil.isNotEmpty;
import static br.com.tremn.crm.model.util.QueryUtil.isNotNull;
import static br.com.tremn.crm.model.util.QueryUtil.toLikeMatchModeSTART;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.tremn.crm.model.entity.Contact;
import br.com.tremn.crm.model.entity.Event;
import br.com.tremn.crm.model.entity.Interaction;
import br.com.tremn.crm.model.entity.Product;
import br.com.tremn.crm.model.entity.Vinculo;
import br.com.tremn.crm.model.entity.enumeration.EventStatus;
import br.com.tremn.crm.model.exception.BusinessException;

/**
 * Servicos de Negocio para Eventos
 * @author Solkam
 * @since 03 MAR 2015
 */
@Stateless
public class EventService {
	
	@PersistenceContext EntityManager manager;
	
	/**
	 * Salva um evento
	 * @param event
	 * @return
	 */
	public Event saveEvent(Event event) {
		verifyEventNameUnique(event);
		return manager.merge( event );
	}


	/**
	 * RN para verificar unicidade do nome de eventos
	 * @param event
	 */
	private void verifyEventNameUnique(Event event) {
		Event foundEvent = findEventByName(event.getName());
		if (foundEvent!=null && !foundEvent.equals(event)) {
			throw new BusinessException("Já existe Evento com este nome");
		}
	}


	/**
	 * Remove um evento
	 * @param event
	 */
	public void removeEvent(Event event) {
		manager.remove( manager.merge( event ) );
	}
	
	
	/**
	 * Recarregar evento e suas associacoes
	 * @param event
	 * @return
	 */
	public Event refreshEvent(Event event) {
		event = manager.find(Event.class, event.getId() );
		event.getVinculos().size();
		event.getPossiblePaymentMethods().size();
		event.getInteractions().size();
		return event;
	}
	

	/**
	 * Busca um evento pelo PK
	 * (usando pelo converter)
	 * @param id
	 * @return
	 */
	public Event findEventById(Long id) {
		return manager.find(Event.class, id);
	}
	
	
	/**
	 * Busca um evento pelo nome
	 * (usado na RN para verificar unicidade do nome de eventos)
	 * @param name
	 * @return
	 */
	private Event findEventByName(String name) {
		try {
			return manager.createNamedQuery("findEventByName", Event.class)
					.setParameter("pName", name)
					.getSingleResult()
					;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	/**
	 * Pesquisa por eventos segundo filtros usando criteria
	 * @param product
	 * @param name
	 * @param year
	 * @param statusList
	 * @return
	 */
	public List<Event> searchEventByFilter(Product product, String name, Integer year, List<EventStatus> statusList) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
		Root<Event> root = criteria.from(Event.class);
		
		Predicate conjunction = builder.conjunction();
		//1.product
		if (isNotNull(product)) {
			conjunction = builder.and(conjunction, 
					builder.equal( root.<Product>get("product"), product )
				);
		}
		//2.name
		if (isNotBlank(name)) {
			conjunction = builder.and(conjunction, 
					builder.like( root.<String>get("name"), toLikeMatchModeSTART(name)) 
				);
		}
		//3.year
		if (isNotNull(year)) {
			conjunction = builder.and(conjunction, 
					builder.equal( builder.function("YEAR", Integer.class, root.<Date>get("beginDate") ), year) 
				);
		}
		//4.status lista
		if (isNotEmpty(statusList)) {
			conjunction = builder.and(conjunction, 
					root.<EventStatus>get("status").in( statusList ) 
				);
		}
		//where e order by
		criteria.where(conjunction);
		criteria.orderBy( builder.asc(root.<Date>get("beginDate")), builder.asc(root.<String>get("name")) );
		
		List<Event> events = manager.createQuery(criteria).getResultList();
		return events;
	}

	
	/**
	 * Pesquisa todos os eventos
	 * @return
	 */
	public List<Event> searchEvent() {
		return manager.createNamedQuery("searchEvent", Event.class)
				.getResultList();
	}

	
	
	/* *******
	 * Vinculo
	 *********/
	/**
	 * Salva um vinculo aplicando RN
	 * @param vinculo
	 * @return
	 */
	public Vinculo saveVinculo(Vinculo vinculo) {
		verifyContactAndEvent(vinculo);
		return manager.merge( vinculo );
	}


	/**
	 * Verifica se contato já está vinculado ao evento
	 * @param vinculo
	 */
	private void verifyContactAndEvent(Vinculo vinculo) {
		Contact contact = vinculo.getContact();
		Event event = vinculo.getEvent();
		Vinculo vinculoFound = findVinculoByContactAndEvent(contact, event);
		if (vinculoFound!=null && !vinculoFound.equals(vinculo)) {
			throw new BusinessException("Contato já está vinculado ao Evento");
		}
	}

	
	/**
	 * Remove um vinculo
	 * @param vinculo
	 */
	public void removeVinculo(Vinculo vinculo) {
		manager.remove( manager.merge(vinculo) );
	}

	
	/**
	 * Busca um vinculo pelo contact e evento.
	 * (usando na RN ao salva vinculo)
	 * @param contact
	 * @param event
	 * @return
	 */
	private Vinculo findVinculoByContactAndEvent(Contact contact, Event event) {
		try {
			return manager.createNamedQuery("findVinculoByContactAndEvent", Vinculo.class)
					.setParameter("pContact", contact)
					.setParameter("pEvent", event)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}




	
	/* **********
	 * Interações
	 ************/
	/**
	 * Salva um interação
	 * @param interaction
	 * @return
	 */
	public Interaction saveInteraction(Interaction interaction) {
		return manager.merge( interaction );
	}
	
	
	/**
	 * Remove uma interação
	 * @param interaction
	 */
	public void removeInteraction(Interaction interaction) {
		interaction = manager.find(Interaction.class, interaction.getId() );
		manager.remove( interaction );
	}


	
}
