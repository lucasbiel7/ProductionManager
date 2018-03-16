/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import br.com.stefanini.model.entity.Atuando.AtuadoID;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "TB_ATUANDO", catalog = Config.SCHEMA)
public class Atuando extends BaseEntity<AtuadoID> {

    @Override
    @EmbeddedId
    public AtuadoID getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    public Atuando() {
        if (getId() == null) {
            setId(new AtuadoID());
        }
    }

    @Embeddable
    public static class AtuadoID implements Serializable {

        private Atuacao atuacao;
        private Usuario usuario;

        @ManyToOne(targetEntity = Atuacao.class, optional = false)
        @JoinColumn(name = "ID_ATUACAO", referencedColumnName = "ID_ATUACAO")
        public Atuacao getAtuacao() {
            return atuacao;
        }

        public void setAtuacao(Atuacao atuacao) {
            this.atuacao = atuacao;
        }

        @ManyToOne(targetEntity = Usuario.class, optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 11 * hash + Objects.hashCode(this.atuacao);
            hash = 11 * hash + Objects.hashCode(this.usuario);
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
            final AtuadoID other = (AtuadoID) obj;
            if (!Objects.equals(this.atuacao, other.atuacao)) {
                return false;
            }
            if (!Objects.equals(this.usuario, other.usuario)) {
                return false;
            }
            return true;
        }

    }
}
