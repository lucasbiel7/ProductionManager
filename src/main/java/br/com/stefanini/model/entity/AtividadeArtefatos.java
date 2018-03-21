/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.entity;

import br.com.stefanini.control.database.Config;
import br.com.stefanini.model.BaseEntity;
import br.com.stefanini.model.entity.AtividadeArtefatos.AtividadeArtefatosId;
import br.com.stefanini.model.enuns.Artefato;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "TB_ATIVIDADE_ARTEFATOS", catalog = Config.SCHEMA)
public class AtividadeArtefatos extends BaseEntity<AtividadeArtefatosId> {

    @Override
    @EmbeddedId
    public AtividadeArtefatosId getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    public AtividadeArtefatos() {

    }

    @Embeddable
    public static class AtividadeArtefatosId implements Serializable {

        private Atividade atividade;

        private Artefato artefato;

        @ManyToOne(targetEntity = Atividade.class, fetch = FetchType.LAZY)
        @JoinColumn(name = "ID_ATIVIDADE", referencedColumnName = "ID_ATIVIDADE")
        public Atividade getAtividade() {
            return atividade;
        }

        public void setAtividade(Atividade atividade) {
            this.atividade = atividade;
        }

        @Column(name = "TP_ARTEFATO")
        @Enumerated(EnumType.STRING)
        public Artefato getArtefato() {
            return artefato;
        }

        public void setArtefato(Artefato artefato) {
            this.artefato = artefato;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + Objects.hashCode(this.atividade);
            hash = 79 * hash + Objects.hashCode(this.artefato);
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
            final AtividadeArtefatosId other = (AtividadeArtefatosId) obj;
            if (!Objects.equals(this.atividade, other.atividade)) {
                return false;
            }
            if (this.artefato != other.artefato) {
                return false;
            }
            return true;
        }

    }
}
