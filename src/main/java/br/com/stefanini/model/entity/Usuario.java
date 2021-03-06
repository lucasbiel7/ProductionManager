/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import br.com.stefanini.model.enuns.TipoPerfil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "TB_USUARIO", catalog = Config.SCHEMA)
public class Usuario extends BaseEntity<String> {

    @Override
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "ID_USUARIO")
    public String getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    private String senha;
    private TipoPerfil perfil;
    private Pessoa pessoa;
    private boolean ativado;
    private List<Atuando> atuando;

    public Usuario() {
        if (atuando == null) {
            atuando = new ArrayList<>();
        }
        if (pessoa == null) {
            pessoa = new Pessoa();
        }
    }

    @Column(name = "TX_SENHA")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

//    @ManyToOne(targetEntity = Perfil.class, optional = false)
//    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
    @Enumerated(EnumType.STRING)
    @Column(name="TP_PERFIL")
    public TipoPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(TipoPerfil perfil) {
        this.perfil = perfil;
    }

    @OneToOne(targetEntity = Pessoa.class, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Column(name = "ST_ATIVO")
    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }

    @OneToMany(mappedBy = "id.usuario",fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Atuando.class)
    public List<Atuando> getAtuando() {
        return atuando;
    }

    public void setAtuando(List<Atuando> atuando) {
        this.atuando = atuando;
    }

}
