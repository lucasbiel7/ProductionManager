/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import br.com.stefanini.model.entity.ProgressoAtividade.ProgressoAtividadeId;
import br.com.stefanini.model.enuns.Faturamento;
import br.com.stefanini.model.enuns.TipoAtividade;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "TB_PROGRESSO_ATIVIDADE",catalog = Config.SCHEMA)
public class ProgressoAtividade extends BaseEntity<ProgressoAtividadeId> {
    
    
    public static class ProgressoAtividadeId implements Serializable {

        private TipoAtividade tipoAtividade;

        private double progresso;

        private Atividade atividade;

        @Enumerated(EnumType.STRING)
        @Column(name = "TP_ATIVIDADE")
        public TipoAtividade getTipoAtividade() {
            return tipoAtividade;
        }

        public void setTipoAtividade(TipoAtividade tipoAtividade) {
            this.tipoAtividade = tipoAtividade;
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

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 83 * hash + Objects.hashCode(this.tipoAtividade);
            hash = 83 * hash + (int) (Double.doubleToLongBits(this.progresso) ^ (Double.doubleToLongBits(this.progresso) >>> 32));
            hash = 83 * hash + Objects.hashCode(this.atividade);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ProgressoAtividadeId other = (ProgressoAtividadeId) obj;
            if (Double.doubleToLongBits(this.progresso) != Double.doubleToLongBits(other.progresso)) {
                return false;
            }
            if (this.tipoAtividade != other.tipoAtividade) {
                return false;
            }
            if (!Objects.equals(this.atividade, other.atividade)) {
                return false;
            }
            return true;
        }
        
        
    }
    

    private Date dataDoProgresso;

    private Faturamento faturamento = Faturamento.AF;
    
    private Date dataFaturamento;

    private Parametro parametroContrato;

    private Parametro parametroRepasse;

    public ProgressoAtividade() {
        if(id==null){
            id =new ProgressoAtividadeId();
        }
    }
    
    @EmbeddedId
    @Override
    public ProgressoAtividadeId getId() {
        return id;
    }

    @Override
    public void setId(ProgressoAtividadeId id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TM_PROGRESSO")
    public Date getDataDoProgresso() {
        return dataDoProgresso;
    }

    public void setDataDoProgresso(Date dataDoProgresso) {
        this.dataDoProgresso = dataDoProgresso;
    }

    @Column(name = "TP_FATURAMENTO")
    @Enumerated(EnumType.STRING)
    public Faturamento getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(Faturamento faturamento) {
        this.faturamento = faturamento;
    }
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_FATURAMENTO")
    public Date getDataFaturamento() {
        return dataFaturamento;
    }

    public void setDataFaturamento(Date dataFaturamento) {
        this.dataFaturamento = dataFaturamento;
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
