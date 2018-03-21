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
    
    private static CustoDAO custoDAO;
    
    private CustoDAO(){
        super();
    }
    
    public static CustoDAO getInstance(){
        if(custoDAO==null){
            custoDAO=new CustoDAO();
        }
        custoDAO.initConfiguration();
        return custoDAO;
    }
    public Custo buscarCustoMes(Date dtInclusao){
        String hql = "SELECT c FROM " + Custo.class.getName() + " c WHERE c.dtInclusao= :dtInclusao ORDER BY c.id DESC";
        Query query = getEntityManager().createQuery(hql);
        query.setParameter("dtInclusao", dtInclusao);
        query.setMaxResults(1); 
        try{
            entity =  (Custo) query.getSingleResult();         
            return entity;
        }catch(NoResultException nre){
            return new Custo(0.0, 0.0, dtInclusao);
        }
        
    }
}
