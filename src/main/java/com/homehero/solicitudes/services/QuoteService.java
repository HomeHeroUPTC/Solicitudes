package com.homehero.solicitudes.services;


import com.homehero.solicitudes.DTOSolicitudes.UserAgendaDTO;
import com.homehero.solicitudes.models.Quote;
import com.homehero.solicitudes.models.Visit;
import com.homehero.solicitudes.repositories.QuoteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.stereotype.Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EntityManager entityManager;

    public void createQuote(Quote quote)  {
        quoteRepository.save(quote);
    }

    public void scheduleQuote(Quote quote) {
        UserAgendaDTO agenda = new UserAgendaDTO(quote);
        String url = "https://msagenda-zaewler4iq-uc.a.run.app/Agenda/Schedule_quote";
        restTemplate.postForEntity(url, agenda, Void.class);
    }

    public List<Quote> getClientQuote(int client_id)  {
        String q = String.format("SELECT q FROM Quote q WHERE q.client_id = %s", client_id);
        TypedQuery<Quote> query = entityManager.createQuery(q, Quote.class);
        return query.getResultList();
    }

    public List<Quote> getHeroQuote(int hero_id)  {
        String q = String.format("SELECT q FROM Quote q WHERE q.hero_id = %s", hero_id);
        TypedQuery<Quote> query = entityManager.createQuery(q, Quote.class);
        return query.getResultList();
    }

    @Transactional
    public void updateQuoteStatus(int quoteId, int status) {
        String q = "UPDATE Quote q SET q.quote_status = :status WHERE q.id = :quoteId";
        Query query = entityManager.createQuery(q);
        query.setParameter("status", status);
        query.setParameter("quoteId", quoteId);
        query.executeUpdate();
    }

    public int getVisitId(int eventId) {
        String q = String.format("SELECT q.visit_id from Quote q  where q.id = %s", eventId);
        TypedQuery<Integer> query = entityManager.createQuery(q, Integer.class);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }
}
