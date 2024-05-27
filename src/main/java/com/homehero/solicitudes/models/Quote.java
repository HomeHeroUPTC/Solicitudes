package com.homehero.solicitudes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private int visit_id;

    @Column(nullable = false)
    private int hero_id;

    @Column(nullable = false)
    private int service_id;

    @Column(nullable = false)
    private int client_id;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 11)
    private String quote_date;

    @Column(nullable = false)
    private int init_time;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int quote_status;

}