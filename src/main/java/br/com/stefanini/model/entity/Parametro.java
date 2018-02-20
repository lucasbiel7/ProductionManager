/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import br.com.stefanini.model.enuns.TipoParametro;
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
 * @author higo
 */
@Entity
@Table(name = "TB_PARAMETRO", schema = Config.SCHEMA)
public class Parametro extends BaseEntity<String> {

    private TipoParametro tipoParametro;
    private Double valor;
    private Date dtInclusao;

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "ID_PARAMETRO")
    @Override
    public String getId() {
        return super.getId();
    }
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TP_PARAMETRO")
    public TipoParametro getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(TipoParametro tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    @Column(name = "VL_PARAMETRO")
    public Double getValor() {
        return valor;
    }
    
    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DT_INCLUSAO")
    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }
}
