package com.ordemservicoapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoExame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "exame_id")
    private Exame exame_id;

    @ManyToOne
    @JoinColumn(name = "ordemservico_id")
    private OrdemServico ordemServico_id;
}
