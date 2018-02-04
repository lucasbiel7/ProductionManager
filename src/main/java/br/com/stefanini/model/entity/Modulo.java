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
@Table(name = "TB_MODULO",schema = "production_manager")
public class Modulo extends BaseEntity<String>{

    private String descricao;
    private Projeto projeto;
    
    @Id
    @GenericGenerator(name= "uuid",strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name="ID_MODULO")
    @Override
    public String getId() {
        return super.getId();
    }
    
    @Column(name = "TX_DESCRICAO")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @ManyToOne(targetEntity = Projeto.class,optional = false)
    @JoinColumn(name = "ID_PROJETO",referencedColumnName = "ID_PROJETO")
    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
}
