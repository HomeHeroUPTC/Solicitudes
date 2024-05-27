package com.homehero.solicitudes.DTOSolicitudes;


import com.homehero.solicitudes.models.Quote;
import com.homehero.solicitudes.models.Visit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAgendaDTO {
    public UserAgendaDTO(Quote quote) {
        this.hero_id = quote.getHero_id();
        this.client_id = quote.getClient_id();
        this.event_id = quote.getId();
        this.event_date = quote.getQuote_date();
        this.hour = quote.getInit_time();
    }

    public UserAgendaDTO(Visit quote) {
        this.hero_id = quote.getHero_id();
        this.client_id = quote.getClient_id();
        this.event_id = quote.getVisit_id();
        this.event_date = quote.getVisit_date();
        this.hour = quote.getInit_time();
    }

    private int hero_id;

    private int client_id;

    private int event_id;

    private String event_date;

    private int hour;

}
