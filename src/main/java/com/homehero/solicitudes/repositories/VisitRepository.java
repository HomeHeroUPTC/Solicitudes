package com.homehero.solicitudes.repositories;

import com.homehero.solicitudes.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
}
