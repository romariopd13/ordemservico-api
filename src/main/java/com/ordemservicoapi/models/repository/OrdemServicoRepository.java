package com.ordemservicoapi.models.repository;

import com.ordemservicoapi.models.entity.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {
}
