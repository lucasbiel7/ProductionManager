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
public enum SituacaoAtividade {
    L("Em Levantamento"),D("Em Desenvolvimento"),T("Em Teste e Homologação"),P("Pendência"),F("Finalizado");
    private String descricao;

    private SituacaoAtividade(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
