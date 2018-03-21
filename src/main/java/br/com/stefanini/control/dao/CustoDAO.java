/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Custo;
import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author higo
 */
public class CustoDAO extends GenericaDAO<Custo>{
    public Custo buscarCustoMes(Date dtInclusao){
        StringBuilder hql = new StringBuilder("SELECT c FROM " + Custo.class.getName()).append(" c WHERE c.dtInclusao= :dtInclusao ORDER BY c.id DESC");
        Query query = getEntityManager().createQuery(hql.toString());
        query.setParameter("dtInclusao", dtInclusao);
        query.setMaxResults(1); 
        try{
            entity =  (Custo) query.getSingleResult();
            getEntityManager().close();
            return entity;
        }catch(NoResultException nre){
            return new Custo(0.0, 0.0, dtInclusao);
        }
        
    }
}
