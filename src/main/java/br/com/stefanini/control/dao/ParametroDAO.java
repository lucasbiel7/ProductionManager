/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Parametro;
import br.com.stefanini.model.enuns.TipoParametro;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */
public class ParametroDAO extends GenericaDAO<Parametro> {
    public List<Parametro> buscaParametrosRecentes(){
        String hql = "SELECT param FROM " + Parametro.class.getName() + " param ORDER BY param.dtInclusao DESC";
        Query query = getEntityManager().createQuery(hql);
        List<Parametro> parametros = query.getResultList();
        List<Parametro> parametrosRecentes = new ArrayList<>();
        parametrosRecentes.add(parametros.stream().filter(param -> param.getTipoParametro()==TipoParametro.CONTRATO).findFirst().orElse(null));
        parametrosRecentes.add(parametros.stream().filter(param -> param.getTipoParametro()==TipoParametro.REPASSE).findFirst().orElse(null));
        getEntityManager().close();
        return parametrosRecentes;
    }
    
    public Parametro buscaParametroRecente(TipoParametro tipoParametro){
        String hql = "SELECT param FROM " + Parametro.class.getName() + " param WHERE param.tipoParametro= :tipoParametro ORDER BY param.dtInclusao DESC";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("tipoParametro", tipoParametro);
        query.setMaxResults(1); 
        try{
            entity=(Parametro) query.getSingleResult();
            getEntityManager().close();
            return entity;
        }catch(NoResultException nre){
            return new Parametro(0.0, tipoParametro);
        }
        
    }
}

