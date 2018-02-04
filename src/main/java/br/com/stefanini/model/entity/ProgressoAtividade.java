/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.model.BaseEntity;
import br.com.stefanini.model.enuns.TipoAtividade;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author lucas
 */
@Entity
@Table(name ="TB_PROGRESSO_ATIVIDADE")
public class ProgressoAtividade extends BaseEntity<String>{

    @Override
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "ID_PROGRESSO_ATIVIDADE")
    public String getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }
    
    private TipoAtividade tipoAtividade;
    
    private Date dataDoProgresso;
    
    private double progresso;

   
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TP_ATIVIDADE")
    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

  
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="TM_PROGRESSO")
    public Date getDataDoProgresso() {
        return dataDoProgresso;
    }

    public void setDataDoProgresso(Date dataDoProgresso) {
        this.dataDoProgresso = dataDoProgresso;
    }

   
    @Column(name = "VL_PROGRESSO",precision = 2)
    public double getProgresso() {
        return progresso;
    }

    public void setProgresso(double progresso) {
        this.progresso = progresso;
    }
    
    
}
