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
public enum TipoAtividade {
    LE("Levantamento"),DE("Desenvolvimento"),TE("Teste e Homologação"), SE("Serviço");
    private String descricao;

    private TipoAtividade(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
