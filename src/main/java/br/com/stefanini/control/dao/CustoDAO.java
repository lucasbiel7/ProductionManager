/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Custo;
import br.com.stefanini.model.util.StringUtil;
import java.util.Date;
import java.util.List;
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
    public Custo buscarCustoMes(Date dtInclusao, String idProjeto){
        StringBuilder hql = new StringBuilder("SELECT c FROM " + Custo.class.getName() + " c WHERE c.dtInclusao= :dtInclusao");
        if(StringUtil.isEmpty(idProjeto)){
            Query query = getEntityManager().createQuery(hql.toString());
            query.setParameter("dtInclusao", dtInclusao);
            List<Custo> custos = query.getResultList();
            Double custoPlanejado = 0.0;
            Double custoRealizado = 0.0;
            for(Custo c : custos){
                if(c.getCustoTecnicoPlanejado() != null){
                    custoPlanejado += c.getCustoTecnicoPlanejado();
                }
                if(c.getCustoTecnicoRealizado() != null){
                    custoRealizado += c.getCustoTecnicoRealizado();
                }  
            }
            Custo custo = new Custo();
            custo.setCustoTecnicoPlanejado(custoPlanejado);
            custo.setCustoTecnicoRealizado(custoRealizado);
            custo.setDtInclusao(dtInclusao);
            return custo;
        }else{
            hql.append( " AND c.projeto.id= :idProjeto");
            Query query = getEntityManager().createQuery(hql.toString());
            query.setParameter("dtInclusao", dtInclusao);
            query.setParameter("idProjeto", idProjeto);
            query.setMaxResults(1); 
            try{
                entity = (Custo) query.getSingleResult();
                return entity;
            }catch(NoResultException nre){
                Custo c = new Custo(0.0, 0.0, dtInclusao);
                return c;
            }
                
        }
    }
}
