/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.enuns;

/**
 *
 * @author lucas
 */
public enum Artefato {
    ES("Estória"), TA("Termo de aceite"), MD("Modelo de dados"), ET("Especificação técnica");

    private final String descricao;

    private Artefato(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

}
