package com.dev.nossaescola.model;

public class Lancamento {
    
    private String dataLanc;
    private String tipo;
    private String categoria;
    private Double valor;
    private String descricao;
    
    public Lancamento() {
        
    }

    public Lancamento(String dataLanc, String tipo, String categoria, Double valor, String descricao) {
        this.dataLanc = dataLanc;
        this.tipo = tipo;
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getDataLanc() {
        return dataLanc;
    }

    public void setDataLanc(String dataLanc) {
        this.dataLanc = dataLanc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    
}
