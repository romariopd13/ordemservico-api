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
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "convenio")
    private String convenio;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente_id;

    @ManyToOne
    @JoinColumn(name = "postocoleta_id")
    private PostoColeta postocoleta_id;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico_id;
}
