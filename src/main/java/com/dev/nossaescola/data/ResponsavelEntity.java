package com.dev.nossaescola.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "Responsavel")
public class ResponsavelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String telefone;
    private String cpf;
    private String endereco;
    
    @Email(message = "Por favor, insira um e-mail válido")
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

}
