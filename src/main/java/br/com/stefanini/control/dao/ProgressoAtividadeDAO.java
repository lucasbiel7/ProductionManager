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
import br.com.stefanini.model.util.StringUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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

    public ProgressoAtividade pegarUtualProgressoPorAtividadeTipo(Atividade atividade, TipoAtividade tipoAtividade) {
        criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("atividade"), atividade), criteriaBuilder.equal(root.get("tipoAtividade"), tipoAtividade)));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dataDoProgresso")));
        try {
            entity = getEntityManager().createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            entity = null;
        }
        getEntityManager().close();
        return entity;
    }

    public List<ProgressoAtividade> pegarEmFaturamentoPorDataTipoAtividade(Date data, TipoAtividade tipoAtividade) {
        if (data == null) {
            return new ArrayList<>();
        }
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

    public void faturar(List<ProgressoAtividade> progressos) {
        getEntityManager().getTransaction().begin();
        for (ProgressoAtividade progresso : progressos) {
            progresso.setFaturamento(Faturamento.FO);
            getEntityManager().merge(progresso);
            getEntityManager().flush();
        }
        getEntityManager().getTransaction().commit();
        getEntityManager().close();
    }

    public Long pegarProgressoAtividade(Date data, TipoAtividade tipoAtividade, String idProjeto, String idModulo, String idPacote) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT count(*) FROM ProgressoAtividade pa WHERE pa.atividade.previsaoInicio =:paramData AND pa.tipoAtividade =:paramTipo ");
        sb.append(" AND pa.progresso =:paramProgresso ");
        if (!StringUtil.isEmpty(idProjeto)) {
            sb.append(" AND pa.atividade.pacote.modulo.projeto.id =:paramIdProjeto ");
        }
        if (!StringUtil.isEmpty(idModulo)) {
            sb.append(" AND pa.atividade.pacote.modulo.id =:paramIdModulo ");
        }
        if (!StringUtil.isEmpty(idPacote)) {
            sb.append(" AND pa.atividade.pacote.id =:paramIDPacote ");
        }
        sb.append(" group by pa.atividade");
        Query query = getEntityManager().createQuery(sb.toString());
        query.setParameter("paramData", new java.sql.Date(data.getTime()));
        query.setParameter("paramTipo", tipoAtividade);
        query.setParameter("paramProgresso", 100.0);

        if (!StringUtil.isEmpty(idProjeto)) {
            query.setParameter("paramIdProjeto", idProjeto);
        }
        if (!StringUtil.isEmpty(idModulo)) {
            query.setParameter("paramIdModulo", idModulo);
        }
        if (!StringUtil.isEmpty(idPacote)) {
            query.setParameter("paramIDPacote", idPacote);
        }
        return (long)query.getResultList().size();
    }

}