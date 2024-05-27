package com.homehero.solicitudes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homehero.solicitudes.DTOSolicitudes.UserAgendaDTO;
import com.homehero.solicitudes.DTOSolicitudes.VisitDto;
import com.homehero.solicitudes.models.Quote;
import com.homehero.solicitudes.models.Visit;
import com.homehero.solicitudes.repositories.VisitRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RestTemplate restTemplate;

    public List<VisitDto> getHeroVisits(int hero_id)  {
        String q = String.format("SELECT v FROM Visit v WHERE v.hero_id = %s", hero_id);
        TypedQuery<Visit> query = entityManager.createQuery(q, Visit.class);
        return convertVisitToDto(query.getResultList());
    }

    public List<VisitDto> convertVisitToDto(List<Visit> visits) {
        List<VisitDto> visitDtos = new ArrayList<>();
        for (Visit visit : visits) {
            VisitDto v = new VisitDto(visit);
            v.setClient_name(getClientName(v.getClient_id()));
            v.setService_name(getServiceName(v.getHero_service_id()));
            v.setImage_url(getServiceImage(v.getHero_service_id()));
            visitDtos.add(v);
        }
        return visitDtos;
    }

    public List<Visit> getClientVisits(int client_id)  {
        String q = String.format("SELECT v FROM Visit v WHERE v.client_id = %s", client_id);
        TypedQuery<Visit> query = entityManager.createQuery(q, Visit.class);
        return query.getResultList();
    }

    public void createVisit(Visit visitDto)  {
        visitRepository.save(visitDto);
    }

    public String getClientName(int client_id) {
        String url = "https://msusuarios-zaewler4iq-uc.a.run.app/User/GetClientName?client_id=" + client_id;
        return restTemplate.getForObject(url, String.class);
    }

    public String getServiceImage(int id) {
        String url = "https://msservice-zaewler4iq-uc.a.run.app/Services/ImgByHeroServiceId?hero_service_id=" + id;
        return restTemplate.getForObject(url, String.class);
    }

    public String getServiceName(int id) {
        String url = "https://msservice-zaewler4iq-uc.a.run.app/Services/NameByHeroServiceId?hero_service_id=" + id;
        return restTemplate.getForObject(url, String.class);
    }

    public void scheduleVisit(Visit visit) {
        UserAgendaDTO agenda = new UserAgendaDTO(visit);
        String url = "https://msagenda-zaewler4iq-uc.a.run.app/Agenda/Schedule_visit";
        restTemplate.postForEntity(url, agenda, Void.class);
    }
}
