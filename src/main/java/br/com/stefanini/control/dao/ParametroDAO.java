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
import java.util.stream.Collectors;
import javax.persistence.Query;

/**
 *
 * @author usuario
 */
public class ParametroDAO extends GenericaDAO<Parametro> {
    public List<Parametro> buscaParametrosRecentes(){
        StringBuilder hql = new StringBuilder("SELECT param FROM " + Parametro.class.getName()).append(" param ORDER BY param.dtInclusao DESC");
        Query query = getEntityManager().createQuery(hql.toString());
        List<Parametro> parametros = query.getResultList();
        List<Parametro> parametrosRecentes = new ArrayList<>();
        parametrosRecentes.add(parametros.stream().filter(param -> param.getTipoParametro()==TipoParametro.CONTRATO).findFirst().orElse(null));
        parametrosRecentes.add(parametros.stream().filter(param -> param.getTipoParametro()==TipoParametro.REPASSE).findFirst().orElse(null));
        return parametrosRecentes;
    }
    
    public Parametro buscaParametroRecente(TipoParametro tipoParametro){
        StringBuilder hql = new StringBuilder("SELECT param FROM " + Parametro.class.getName()).append(" param WHERE param.tipoParametro= :tipoParametro ORDER BY param.dtInclusao DESC");
        Query query = getEntityManager().createQuery(hql.toString());
        query.setParameter("tipoParametro", tipoParametro);
        query.setMaxResults(1); 
        try{
            return (Parametro) query.getSingleResult();
        }catch(NoResultException nre){
            return new Parametro(0.0, tipoParametro);
        }
        
    }
}

