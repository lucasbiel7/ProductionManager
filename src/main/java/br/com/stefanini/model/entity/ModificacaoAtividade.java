/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.enuns.TipoAtividade;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "TB_MODIFICACAO_ATIVIDADE", schema = Config.SCHEMA)
@PrimaryKeyJoinColumn(name = "ID_ATIVIDADE", referencedColumnName = "ID_ATIVIDADE")
public class ModificacaoAtividade extends Atividade {

    private String descricaoModificacao;

    private TipoAtividade tipoAtividade;

    private Atividade atividade;
    private Date dataModificacao;

    public ModificacaoAtividade() {
    }

    public ModificacaoAtividade(Atividade atividade) {
        super(atividade);
        this.atividade = atividade;
    }

    @Column(name = "TX_DESCRICAO_MODIFICACAO")
    @Lob
    public String getDescricaoModificacao() {
        return descricaoModificacao;
    }

    public void setDescricaoModificacao(String descricaoModificacao) {
        this.descricaoModificacao = descricaoModificacao;
    }

    @Column(name = "TP_ATIVIDADE", nullable = false)
    @Enumerated(EnumType.STRING)
    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    @ManyToOne(targetEntity = Atividade.class, optional = false)
    @JoinColumn(name = "ID_ATIVIDADE_PRINCIPAL", referencedColumnName = "ID_ATIVIDADE")
    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    @Column(name = "DT_MODIFICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
