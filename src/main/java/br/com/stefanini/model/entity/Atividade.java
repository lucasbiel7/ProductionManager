/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    private long contagemEstimada;
    private long contagemDetalhada;
    private OrdemServico ordemServico;
    private Pacote pacote;
    private Date previsaoInicio;

    private List<AtividadeArtefatos> atividadeArtefatos;

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
    public long getContagemEstimada() {
        return contagemEstimada;
    }

    public void setContagemEstimada(long contagemEstimada) {
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
    public long getContagemDetalhada() {
        return contagemDetalhada;
    }

    public void setContagemDetalhada(long contagemDetalhada) {
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
}
