/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import br.com.stefanini.model.enuns.Faturamento;
import br.com.stefanini.model.enuns.SituacaoAtividade;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
@Table(name = "TB_ATIVIDADE", catalog = Config.SCHEMA)
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = Atividade.PEGAR_POR_DIA, query = "select a from Atividade a where a.previsaoInicio=:previsaoInicio")
public class Atividade extends BaseEntity<String> {

    public static final String PEGAR_POR_DIA = "Atividade.pegarPorMes";

    private String descricao;
    private Double contagemEstimada;
    private Double contagemDetalhada;
    private OrdemServico ordemServico;
    private Pacote pacote;
    private Faturamento faturamento;
    private SituacaoAtividade situacaoAtividade;
    private Date previsaoInicio;
    private List<AtividadeArtefatos> atividadeArtefatos;
    private Double aliEstimada;
    private Double aliDetalhada;
    private List<ProgressoAtividade> progressos;

    public Atividade() {
    }

    public Atividade(Atividade atividade) {
        this.descricao = atividade.getDescricao();
        this.contagemEstimada = atividade.getContagemEstimada();
        this.contagemDetalhada = atividade.getContagemDetalhada();
        this.ordemServico = atividade.getOrdemServico();
        this.pacote = atividade.getPacote();
        this.faturamento = atividade.getFaturamento();
        this.situacaoAtividade = atividade.getSituacaoAtividade();
        this.previsaoInicio = atividade.getPrevisaoInicio();

    }

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
    @Column(name = "FL_FATURAMENTO", length = 2)
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
    @Column(name = "FL_SITUACAO_ATIVIDADE")
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

    @OneToMany(mappedBy = "id.atividade", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<AtividadeArtefatos> getAtividadeArtefatos() {
        return atividadeArtefatos;
    }

    public void setAtividadeArtefatos(List<AtividadeArtefatos> atividadeArtefatos) {
        this.atividadeArtefatos = atividadeArtefatos;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    /**
     * @return the progressos
     */
    @OneToMany(mappedBy = "id.atividade", fetch = FetchType.LAZY, targetEntity = ProgressoAtividade.class)
    public List<ProgressoAtividade> getProgressos() {
        return progressos;
    }

    /**
     * @param progressos the progressos to set
     */
    public void setProgressos(List<ProgressoAtividade> progressos) {
        this.progressos = progressos;
    }

    @Column(name = "VL_ALI_ESTIMADA", nullable = true)
    public Double getAliEstimada() {
        return aliEstimada;
    }

    public void setAliEstimada(Double aliEstimada) {
        this.aliEstimada = aliEstimada;
    }

    @Column(name = "VL_ALI_DETALHADA", nullable = true)
    public Double getAliDetalhada() {
        return aliDetalhada;
    }

    public void setAliDetalhada(Double aliDetalhada) {
        this.aliDetalhada = aliDetalhada;
    }

}
