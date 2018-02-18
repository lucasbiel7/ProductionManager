/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import br.com.stefanini.model.enuns.Faturamento;
import br.com.stefanini.model.enuns.Mes;
import br.com.stefanini.model.enuns.SituacaoAtividade;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "TB_ATIVIDADE", schema = Config.SCHEMA)
public class Atividade extends BaseEntity<String> {

    private String descricao;
    private Double contagemEstimada;
    private Double contagemDetalhada;
    private OrdemServico ordemServico;
    private Pacote pacote;
    private Faturamento faturamento;
    private SituacaoAtividade situacaoAtividade;
    private Date previsaoInicio;
    private List<AtividadeArtefatos> atividadeArtefatos;
    private Mes mes;

    @Override
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "ID_ATIVIDADE")
    public String getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Column(name = "TX_DESCRICAO")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(name = "VL_ESTIMADA")
    public Double getContagemEstimada() {
        return contagemEstimada;
    }

    public void setContagemEstimada(Double contagemEstimada) {
        this.contagemEstimada = contagemEstimada;
    }

    @ManyToOne(targetEntity = OrdemServico.class, optional = false)
    @JoinColumn(name = "ID_ORDEM_SERVICO", referencedColumnName = "ID_ORDEM_SERVICO")
    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    @Column(name = "VL_DETALHADA") 
    public Double getContagemDetalhada() {
        return contagemDetalhada;
    }

    public void setContagemDetalhada(Double contagemDetalhada) {
        this.contagemDetalhada = contagemDetalhada;
    }

    @ManyToOne(targetEntity = Pacote.class, optional = false)
    @JoinColumn(name = "ID_PACOTE", referencedColumnName = "ID_PACOTE")
    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    /**
     * @return the faturamento
     */
    @Column(name="FL_FATURAMENTO",length = 2)
    @Enumerated(EnumType.STRING)
    public Faturamento getFaturamento() {
        return faturamento;
    }

    /**
     * @param faturamento the faturamento to set
     */
    public void setFaturamento(Faturamento faturamento) {
        this.faturamento = faturamento;
    }

    /**
     * @return the situacaoAtividade
     */
    @Column(name="FL_SITUACAO_ATIVIDADE")
    @Enumerated(EnumType.STRING)
    public SituacaoAtividade getSituacaoAtividade() {
        return situacaoAtividade;
    }

    /**
     * @param situacaoAtividade the situacaoAtividade to set
     */
    public void setSituacaoAtividade(SituacaoAtividade situacaoAtividade) {
        this.situacaoAtividade = situacaoAtividade;
    }

    @Column(name = "DT_PREVISAO_INICIO")
    @Temporal(TemporalType.DATE)
    public Date getPrevisaoInicio() {
        return previsaoInicio;
    }

    public void setPrevisaoInicio(Date previsaoInicio) {
        this.previsaoInicio = previsaoInicio;
    }

    @OneToMany(mappedBy = "id.atividade", orphanRemoval = true, cascade = CascadeType.ALL)
    public List<AtividadeArtefatos> getAtividadeArtefatos() {
        return atividadeArtefatos;
    }

    public void setAtividadeArtefatos(List<AtividadeArtefatos> atividadeArtefatos) {
        this.atividadeArtefatos = atividadeArtefatos;
    }

    /**
     * @return the mes
     */
    @Column(name = "FL_MES")
    @Enumerated(EnumType.STRING)
    public Mes getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(Mes mes) {
        this.mes = mes;
    }
}
