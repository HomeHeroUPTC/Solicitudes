package com.homehero.solicitudes.services;


import com.homehero.solicitudes.DTOSolicitudes.UserAgendaDTO;
import com.homehero.solicitudes.models.Quote;
import com.homehero.solicitudes.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createQuote(Quote quote)  {
        quoteRepository.save(quote);
    }

    public void scheduleQuote(Quote quote) {
        UserAgendaDTO agenda = new UserAgendaDTO(quote);
        String url = "https://msagenda-zaewler4iq-uc.a.run.app/Agenda/Schedule_quote";
        restTemplate.postForEntity(url, agenda, Void.class);
    }
}
