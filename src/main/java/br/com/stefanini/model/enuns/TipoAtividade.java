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
    LE("Levantamento","Levantamento - 35%"),DE("Desenvolvimento","Desenvolvimento - 40%"),
    TE("Teste e Homologação","Teste e Homologação - 25%"), SE("Serviço","Consultoria"),
    I("Inclusão","Inclusão"), A("Alteração","Alteração");
    private String descricao;
    private String pqp;

    private TipoAtividade(String descricao,String pqp) {
        this.descricao = descricao;
        this.pqp = pqp;
    }

    @Override
    public String toString() {
        return descricao;
    }

    /**
     * @return the pqp
     */
    public String getPqp() {
        return pqp;
    }

    /**
     * @param pqp the pqp to set
     */
    public void setPqp(String pqp) {
        this.pqp = pqp;
    }
}
