/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author lucas
 */
@Entity
@Table(name="TB_ORDEM_SERVICO",schema = "production_manager")
public class OrdemServico extends BaseEntity<String>{

    private String descricao;
    private Modulo modulo;
    
    @Override
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name="ID_ORDEM_SERVICO")
    public String getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Column(name="TX_DESCRICAO")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }  

    @ManyToOne(targetEntity = Modulo.class,optional = false)
    @JoinColumn(name = "ID_MODULO",referencedColumnName = "ID_MODULO")
    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    
}
