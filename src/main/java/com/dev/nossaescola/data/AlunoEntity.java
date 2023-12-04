package com.dev.nossaescola.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "aluno")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String dataNasc;
    private String sexo;
    private String nomeMae;
    private String turno;

    @Column(name = "mensalidade", columnDefinition = "NUMERIC")
    private Double mensalidade;
}
