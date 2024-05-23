package com.homehero.solicitudes.controllers;

import com.homehero.solicitudes.models.ErrorResponse;
import com.homehero.solicitudes.models.Visit;
import com.homehero.solicitudes.services.VisitService;
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
            List<Visit> visits = serviceService.getHeroVisits(hero_id);
            return new ResponseEntity<>(visits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/CreateVisit")
    public ResponseEntity<?> CreateHeroService(@RequestBody Visit visit){
        try {
            visitService.createVisit(visit);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}