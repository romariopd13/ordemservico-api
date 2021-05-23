package com.ordemservicoapi.models.repository;

import com.ordemservicoapi.models.entity.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<Exame, Integer> {
}
