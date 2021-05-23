package com.ordemservicoapi.models.repository;

import com.ordemservicoapi.models.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}
