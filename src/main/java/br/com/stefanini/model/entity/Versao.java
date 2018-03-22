/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author higo
 */
@Entity
@Table(name = "TB_VERSAO", catalog = Config.SCHEMA)
public class Versao extends BaseEntity<String> {
    
    private Double nrVersao;
    private Date dtInclusao;
    
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "ID_VERSAO")
    @Override
    public String getId() {
        return super.getId();
    }

    @Column(name = "NR_VERSAO")
    public Double getNrVersao() {
        return nrVersao;
    }

    public void setNrVersao(Double nrVersao) {
        this.nrVersao = nrVersao;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO")
    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }
    
}
