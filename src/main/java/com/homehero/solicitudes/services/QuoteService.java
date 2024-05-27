package com.homehero.solicitudes.services;


import com.homehero.solicitudes.models.Quote;
import com.homehero.solicitudes.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public void createQuote(Quote quote)  {
        quoteRepository.save(quote);
    }

}
