/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.util.StringUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author lucas
 */
public class AtividadeDAO extends GenericaDAO<Atividade> {

    public List<Atividade> pegarPorMes(Date data) {
        criteriaQuery.where(criteriaBuilder.equal(root.<java.sql.Date>get("previsaoInicio"), new java.sql.Date(data.getTime())));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();
        getEntityManager().close();
        return entitys;
    }

    public List<Atividade> pegarPorAtividade(Atividade atividade) {
        List<Predicate> criterios = new ArrayList<>();
        if (atividade.getPacote().getModulo().getProjeto().getId() != null) {
            criterios.add(criteriaBuilder.equal(root.get("pacote").get("modulo").get("projeto"), atividade.getPacote().getModulo().getProjeto()));
        }

        if (atividade.getPacote().getModulo().getId() != null) {
            criterios.add(criteriaBuilder.equal(root.get("pacote").get("modulo"), atividade.getPacote().getModulo()));
        }

        if (atividade.getPacote().getId() != null) {
            criterios.add(criteriaBuilder.equal(root.get("pacote"), atividade.getPacote()));
        }

        if (!StringUtil.isEmpty(atividade.getDescricao())) {
            criterios.add(criteriaBuilder.like(root.get("descricao"), atividade.getDescricao() + "%"));
        }

        if (atividade.getSituacaoAtividade() != null) {
            criterios.add(criteriaBuilder.equal(root.get("situacaoAtividade"), atividade.getSituacaoAtividade()));
        }

        if (atividade.getFaturamento() != null) {
            criterios.add(criteriaBuilder.equal(root.get("faturamento"), atividade.getFaturamento()));
        }
        criteriaQuery.where(criteriaBuilder.and(criterios.toArray(new Predicate[]{})));

        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();
        getEntityManager().close();
        return entitys;
    }
    
    public List<Atividade> buscarAtividade(String idProjeto, String idModulo, String idPacote){
        StringBuilder hql = new StringBuilder("SELECT a FROM " + Atividade.class.getName()).append(" a ");
        
        if(!StringUtil.isEmpty(idProjeto) && !StringUtil.isEmpty(idModulo) && !StringUtil.isEmpty(idPacote)){
            hql.append(" WHERE a.pacote.id = :idPacote AND a.pacote.modulo.id = :idModulo AND a.pacote.modulo.projeto.id = :idProjeto");
        }
        else if(!StringUtil.isEmpty(idModulo) && !StringUtil.isEmpty(idProjeto)) {
            hql.append(" WHERE a.pacote.modulo.id = :idModulo AND a.pacote.modulo.projeto.id = :idProjeto");
        }
        else if(!StringUtil.isEmpty(idProjeto)){
            hql.append(" WHERE a.pacote.modulo.projeto.id = :idProjeto");
        }
        
        Query query = getEntityManager().createQuery(hql.toString());
        if(!StringUtil.isEmpty(idPacote)){
            query.setParameter("idPacote", idPacote);
        }
        if(!StringUtil.isEmpty(idModulo)){
            query.setParameter("idModulo", idModulo);
        }
        if(!StringUtil.isEmpty(idProjeto)){
            query.setParameter("idProjeto", idProjeto);
        }
        return query.getResultList();
    }

    public Atividade carregarArtefatos(Atividade atividade) {
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), atividade.getId()));
        root.fetch("atividadeArtefatos", JoinType.LEFT);
        entity = getEntityManager().createQuery(criteriaQuery).getSingleResult();
        getEntityManager().close();
        return entity;
    }
}
