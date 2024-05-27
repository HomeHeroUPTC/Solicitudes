package com.homehero.solicitudes.DTOSolicitudes;

import com.homehero.solicitudes.models.Visit;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VisitDto {

    public VisitDto(Visit visit) {
        this.visit_id = visit.getVisit_id();
        this.hero_id = visit.getHero_id();
        this.hero_service_id = visit.getHero_service_id();
        this.visit_date = visit.getVisit_date();
        this.client_id = visit.getClient_id();
        this.init_time = visit.getInit_time();
        this.address = visit.getAddress();
        this.visit_status = visit.getVisit_status();
    }

    private int visit_id;

    private int hero_id;

    private int hero_service_id;

    private int client_id;

    private String visit_date;

    private int init_time;

    private String address;

    private int visit_status;

    private String client_name;

    private String service_name;

    private String image_url;
}
