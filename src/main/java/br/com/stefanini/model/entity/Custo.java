/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author higo
 */
@Entity
@Table(name = "TB_CUSTO", catalog = Config.SCHEMA)
public class Custo extends BaseEntity<String> {

    private Double custoTecnicoPlanejado = 0.0;
    private Double custoTecnicoRealizado = 0.0;
    private Date dtInclusao;
    private Projeto projeto;

    public Custo() {
    }
    
    public Custo(Double custoTecnicoPlanejado, Double custoTecnicoRealizado, Date dtInclusao) {
        this.custoTecnicoPlanejado = custoTecnicoPlanejado;
        this.custoTecnicoRealizado = custoTecnicoRealizado;
        this.dtInclusao = dtInclusao;
    }
    
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "ID_CUSTO")
    @Override
    public String getId() {
        return super.getId();
    }

    @Column(name = "VL_PLANEJADO")
    public Double getCustoTecnicoPlanejado() {
        return custoTecnicoPlanejado;
    }

    public void setCustoTecnicoPlanejado(Double custoTecnicoPlanejado) {
        this.custoTecnicoPlanejado = custoTecnicoPlanejado;
    }

    @Column(name = "VL_REALIZADO")
    public Double getCustoTecnicoRealizado() {
        return custoTecnicoRealizado;
    }

    public void setCustoTecnicoRealizado(Double custoTecnicoRealizado) {
        this.custoTecnicoRealizado = custoTecnicoRealizado;
    }

    @Column(name="DT_INCLUSAO")
    @Temporal(TemporalType.DATE)
    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }
    
    @OneToOne(targetEntity = Projeto.class, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PROJETO", referencedColumnName = "ID_PROJETO")
    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
}
