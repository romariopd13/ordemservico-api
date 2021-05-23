package com.ordemservicoapi.models.repository;

import com.ordemservicoapi.models.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
