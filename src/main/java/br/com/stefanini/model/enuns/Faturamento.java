/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.enuns;

/**
 *
 * @author rkkitagawa
 */
public enum Faturamento {
    AF("Aguardando Faturamento"),EF("Em Faturamento"),FO("Faturado");
    private String descricao;

    private Faturamento(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
