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
public enum TipoPerfil {
    BDMG("BDMG"),
    GERENTE("Gerente"),
    ANALISTA("Analista"),
    BANCO_DADOS("Banco de Dados"),
    DESENVOLVEDOR("Desenvolvedor"),
    QUALIDADE("Qualidade"),
    ADMINISTRADOR("Administrador");
    
    public String descricao;
    
    TipoPerfil(String descricao){
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return this.descricao;
    }
}
