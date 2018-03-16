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
public enum OrigemAtividade {
    
    P("Produto"),
    S("Serviço");
    
    private String descricao;

    private OrigemAtividade(String descricao){
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}
