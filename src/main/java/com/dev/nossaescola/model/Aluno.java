package com.dev.nossaescola.model;

public class Aluno {

    private int id;
    private String nome;
    private String dataNasc;
    private String sexo;
    private String nomeMae;
    private String turno;
    private Double mensalidade ;
    private String convenio;

    public Aluno() {
    }

    public Aluno(int id, String nome, String dataNasc, String sexo, String nomeMae, String turno, Double mensalidade, String convenio) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.nomeMae = nomeMae;
        this.turno = turno;
        this.mensalidade = mensalidade;
        this.convenio = convenio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    

}
