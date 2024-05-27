package com.homehero.solicitudes.repositories;

import com.homehero.solicitudes.models.Quote;
import com.homehero.solicitudes.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
}
