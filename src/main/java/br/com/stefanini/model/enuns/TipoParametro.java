/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.enuns;

/**
 *
 * @author higo
 */
public enum TipoParametro {
    CONTRATO("Contrato"),
    REPASSE("Repasse");
    
    private String nome;

    private TipoParametro(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
