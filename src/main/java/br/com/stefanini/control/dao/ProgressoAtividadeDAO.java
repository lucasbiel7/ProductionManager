/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Atividade;
import br.com.stefanini.model.entity.ProgressoAtividade;
import br.com.stefanini.model.enuns.TipoAtividade;
import java.util.List;

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

}
