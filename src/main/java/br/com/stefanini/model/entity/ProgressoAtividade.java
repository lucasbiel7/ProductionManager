/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import br.com.stefanini.model.enuns.Faturamento;
import br.com.stefanini.model.enuns.TipoAtividade;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "TB_PROGRESSO_ATIVIDADE", schema = Config.SCHEMA)
public class ProgressoAtividade extends BaseEntity<String> {

    @Override
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "ID_PROGRESSO_ATIVIDADE")
    public String getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    private TipoAtividade tipoAtividade;

    private Date dataDoProgresso;

    private Date dataInicio;

    private Date dataFim;

    private double progresso;

    private Atividade atividade;

    private Faturamento faturamento = Faturamento.AF;

    private Parametro parametroContrato;

    private Parametro parametroRepasse;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_ATIVIDADE")
    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TM_PROGRESSO")
    public Date getDataDoProgresso() {
        return dataDoProgresso;
    }

    public void setDataDoProgresso(Date dataDoProgresso) {
        this.dataDoProgresso = dataDoProgresso;
    }

    @Column(name = "VL_PROGRESSO", precision = 2)
    public double getProgresso() {
        return progresso;
    }

    public void setProgresso(double progresso) {
        this.progresso = progresso;
    }

    @ManyToOne(targetEntity = Atividade.class)
    @JoinColumn(name = "ID_ATIVIDADE", referencedColumnName = "ID_ATIVIDADE")
    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    /**
     * @return the dataInicio
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TM_INICIO")
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TM_FIM")
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Column(name = "TP_FATURAMENTO")
    @Enumerated(EnumType.STRING)
    public Faturamento getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(Faturamento faturamento) {
        this.faturamento = faturamento;
    }

    @ManyToOne(targetEntity = Parametro.class, optional = true)
    @JoinColumn(name = "ID_PARAMETRO_CONTRATO", referencedColumnName = "ID_PARAMETRO")
    public Parametro getParametroContrato() {
        return parametroContrato;
    }

    public void setParametroContrato(Parametro parametroContrato) {
        this.parametroContrato = parametroContrato;
    }

    @ManyToOne(targetEntity = Parametro.class, optional = true)
    @JoinColumn(name = "ID_PARAMETRO_REPASSE", referencedColumnName = "ID_PARAMETRO")
    public Parametro getParametroRepasse() {
        return parametroRepasse;
    }

    public void setParametroRepasse(Parametro parametroRepasse) {
        this.parametroRepasse = parametroRepasse;
    }
}
