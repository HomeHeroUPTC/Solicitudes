package com.homehero.solicitudes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homehero.solicitudes.models.Visit;
import com.homehero.solicitudes.repositories.VisitRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Visit> getHeroVisits(int hero_id)  {
        String q = String.format("SELECT v FROM Visit v WHERE v.hero_id = %s", hero_id);
        TypedQuery<Visit> query = entityManager.createQuery(q, Visit.class);
        return query.getResultList();
    }

    public List<Visit> getClientVisits(int client_id)  {
        String q = String.format("SELECT v FROM Visit v WHERE v.client_id = %s", client_id);
        TypedQuery<Visit> query = entityManager.createQuery(q, Visit.class);
        return query.getResultList();
    }

    public void createVisit(Visit visitDto)  {
        visitRepository.save(visitDto);
    }

}
