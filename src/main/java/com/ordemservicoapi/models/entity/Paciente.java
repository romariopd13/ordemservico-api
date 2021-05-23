package com.ordemservicoapi.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "nascimento")
    private String nascimento;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "endereco")
    private String endereco;
}
