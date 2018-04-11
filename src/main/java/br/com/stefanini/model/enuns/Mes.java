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
public enum Mes {
    JAN("Janeiro"),
    FEV("Fevereiro"),
    MAR("Março"),
    ABR("Abril"),
    MAI("Maio"),
    JUN("Junho"),
    JUL("Julho"),
    AGO("Agosto"),
    SET("Setembro"),
    OUT("Outubro"),
    NOV("Novembro"),
    DEZ("Dezembro");

    private final String descricao;

    private Mes(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

}
