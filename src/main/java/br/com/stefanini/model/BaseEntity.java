/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;

/**
 *
 * @author lucas
 * @param <Type>
 */
public abstract class BaseEntity<Type extends Serializable> implements Serializable{
    
    @Access(AccessType.PROPERTY)
    protected Type id;

    public Type getId() {
        return id;
    }

    public void setId(Type id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final BaseEntity<?> other = (BaseEntity<?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
