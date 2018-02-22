/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.enuns.Faturamento;
import br.com.stefanini.model.enuns.TipoAtividade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author lucas
 */
public class ProgressoAtividadeDAO extends GenericaDAO<ProgressoAtividade> {

    public List<ProgressoAtividade> pegarPorAtividadeTipo(Atividade atividade, TipoAtividade tipoAtividade) {
        criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("atividade"), atividade), criteriaBuilder.equal(root.get("tipoAtividade"), tipoAtividade)));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dataDoProgresso")));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();
        getEntityManager().close();
        return entitys;
    }

    public List<ProgressoAtividade> pegarEmFaturamentoPorDataTipoAtividade(Date data, TipoAtividade tipoAtividade) {
        List<Predicate> criterios = new ArrayList<>();
        //TODO ISSUE DO LUCAS (se der bug)
        criterios.add(criteriaBuilder.equal(root.get("atividade").<java.sql.Date>get("previsaoInicio"), new java.sql.Date(data.getTime())));
        criterios.add(criteriaBuilder.equal(root.get("tipoAtividade"), tipoAtividade));
        criterios.add(criteriaBuilder.equal(root.get("faturamento"), Faturamento.EF));
        criteriaQuery.where(criteriaBuilder.and(criterios.toArray(new Predicate[]{})));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();
        getEntityManager().close();
        return entitys;
    }
    
    public void faturar(List<ProgressoAtividade> progressos){        
        getEntityManager().getTransaction().begin();        
        for (ProgressoAtividade progresso : progressos) {
            progresso.setFaturamento(Faturamento.FO);
            getEntityManager().merge(progresso);
            getEntityManager().flush();        
        }
        getEntityManager().getTransaction().commit();
        getEntityManager().close();
    }

}
