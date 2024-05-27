package com.homehero.solicitudes.controllers;

import com.homehero.solicitudes.DTOSolicitudes.VisitDto;
import com.homehero.solicitudes.models.ErrorResponse;
import com.homehero.solicitudes.models.Quote;
import com.homehero.solicitudes.models.Visit;
import com.homehero.solicitudes.services.VisitService;
import com.homehero.solicitudes.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Solicitudes")
public class Controller {

    @Autowired
    private VisitService serviceService;
    @Autowired
    private VisitService visitService;
    @Autowired
    private QuoteService quoteService;


    @GetMapping(value = "/GetClientVisits")
    public ResponseEntity<?> getServices(@RequestParam int client_id) {
        try {
            List<Visit> visits = serviceService.getClientVisits(client_id);
            return new ResponseEntity<>(visits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/GetHeroVisits")
    public ResponseEntity<?> GetHeroVisits(@RequestParam int hero_id) {
        try {
            List<VisitDto> visits = serviceService.getHeroVisits(hero_id);
            return new ResponseEntity<>(visits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/CreateVisit")
    public ResponseEntity<?> CreateHeroService(@RequestBody Visit visit){
        try {
            visit.setVisit_status(1);
            visitService.createVisit(visit);
            visitService.scheduleVisit(visit);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/CreateQuote")
    public ResponseEntity<?> CreateHeroService(@RequestBody Quote quote){
        try {
            quote.setQuote_status(1);
            quoteService.createQuote(quote);
            quoteService.scheduleQuote(quote);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/GetHeroQuotes")
    public ResponseEntity<?> GetHeroQuotes(@RequestParam int hero_id) {
        try {
            List<Quote> quotes = quoteService.getHeroQuote(hero_id);
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/GetClientQuotes")
    public ResponseEntity<?> GetClientQuotes(@RequestParam int client_id) {
        try {
            List<Quote> quotes = quoteService.getClientQuote(client_id);
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/UpdateVisitStatus")
    public ResponseEntity<?> UpdateVisitStatus(@RequestParam int visit_id, @RequestParam int status){
        try {
            visitService.updateVisitStatus(visit_id, status);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/UpdateQuoteStatus")
    public ResponseEntity<?> UpdateQuoteStatus(@RequestParam int quote_id, @RequestParam int status){
        try {
            quoteService.updateQuoteStatus(quote_id, status);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
