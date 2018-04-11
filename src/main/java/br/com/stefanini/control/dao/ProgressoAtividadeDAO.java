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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author lucas
 */
public class ProgressoAtividadeDAO extends GenericaDAO<ProgressoAtividade> {

    private static ProgressoAtividadeDAO progressoAtividadeDAO;

    public static ProgressoAtividadeDAO getInstance() {
        if (progressoAtividadeDAO == null) {
            progressoAtividadeDAO = new ProgressoAtividadeDAO();
        }
        progressoAtividadeDAO.initConfiguration();
        return progressoAtividadeDAO;
    }

    private ProgressoAtividadeDAO() {
        super();
    }

    public List<ProgressoAtividade> pegarPorAtividadeTipo(Atividade atividade, TipoAtividade tipoAtividade) {
        criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("atividade"), atividade), criteriaBuilder.equal(root.get("tipoAtividade"), tipoAtividade)));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dataDoProgresso")));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();

        return entitys;
    }

    public Double pegarUtualProgressoPorAtividadeTipo(Atividade atividade, TipoAtividade tipoAtividade) {
        TypedQuery<Double> typedQuery = getEntityManager().
                createQuery("SELECT pa.id.progresso FROM ProgressoAtividade pa where pa.id.atividade =:atividade and pa.id.tipoAtividade=:tipoAtividade ORDER by pa.id.progresso DESC",
                        Double.class);
        typedQuery.setParameter("atividade", atividade);
        typedQuery.setParameter("tipoAtividade", tipoAtividade);
        double valor;
        try {
            valor = typedQuery.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            valor = 0d;
        }

        return valor;
    }

    public List<ProgressoAtividade> pegarEmFaturamentoPorDataTipoAtividade(Date data, TipoAtividade tipoAtividade) {
        if (data == null) {
            return new ArrayList<>();
        }
        List<Predicate> criterios = new ArrayList<>();
        //TODO ISSUE DO LUCAS (se der bug)
        criterios.add(criteriaBuilder.lessThanOrEqualTo(root.get("id").get("atividade").<java.sql.Date>get("previsaoInicio"), new java.sql.Date(data.getTime())));
        criterios.add(criteriaBuilder.equal(root.get("id").get("tipoAtividade"), tipoAtividade));
        criterios.add(criteriaBuilder.equal(root.get("faturamento"), Faturamento.EF));
        criteriaQuery.where(criteriaBuilder.and(criterios.toArray(new Predicate[]{})));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();

        return entitys;
    }
    
    public List<ProgressoAtividade> pegarFaturadosPorDataTipoAtividade(Date data, TipoAtividade tipoAtividade) {
        if (data == null) {
            return new ArrayList<>();
        }
        List<Predicate> criterios = new ArrayList<>();
        //TODO ISSUE DO LUCAS (se der bug)TEU CU 
        criterios.add(criteriaBuilder.equal(root.<java.sql.Date>get("dataFaturamento"), new java.sql.Date(data.getTime())));
        criterios.add(criteriaBuilder.equal(root.get("id").get("tipoAtividade"), tipoAtividade));
        criterios.add(criteriaBuilder.equal(root.get("faturamento"), Faturamento.FO));
        criteriaQuery.where(criteriaBuilder.and(criterios.toArray(new Predicate[]{})));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();

        return entitys;
    }
    
    public List<ProgressoAtividade> pesquisaFasesComFiltros(Date data, Atividade atv, TipoAtividade tipoAtividade, Faturamento faturamento) {
        List<Predicate> criterios = new ArrayList<>();
        if(faturamento.equals(Faturamento.FO)){
            // FATURADOS
            criterios.add(criteriaBuilder.equal(root.<java.sql.Date>get("dataFaturamento"), new java.sql.Date(data.getTime())));
        }else{
            // EM FATURAMENTO
            criterios.add(criteriaBuilder.lessThanOrEqualTo(root.get("id").get("atividade").<java.sql.Date>get("previsaoInicio"), new java.sql.Date(data.getTime())));
        }
        
        criterios.add(criteriaBuilder.equal(root.get("id").get("tipoAtividade"), tipoAtividade));
        criterios.add(criteriaBuilder.equal(root.get("faturamento"), faturamento));
        if (atv.getPacote().getModulo().getProjeto().getId() != null) {
            criterios.add(criteriaBuilder.equal(root.get("id").get("atividade").get("pacote").get("modulo").get("projeto"), atv.getPacote().getModulo().getProjeto()));
        }
        if (atv.getPacote().getModulo().getId() != null) {
            criterios.add(criteriaBuilder.equal(root.get("id").get("atividade").get("pacote").get("modulo"), atv.getPacote().getModulo()));
        }
        if (atv.getPacote().getId() != null) {
            criterios.add(criteriaBuilder.equal(root.get("id").get("atividade").get("pacote"), atv.getPacote()));
        }
        criteriaQuery.where(criteriaBuilder.and(criterios.toArray(new Predicate[]{})));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();

        return entitys;
    }
    

    public void faturar(List<ProgressoAtividade> progressos,Date data) {
        getEntityManager().getTransaction().begin();
        for (ProgressoAtividade progresso : progressos) {
            progresso.setFaturamento(Faturamento.FO);
            progresso.setDataFaturamento(data);
            if(progresso.getId().getTipoAtividade().equals(TipoAtividade.TE) 
                    && progresso.getFaturamento().equals(Faturamento.FO)){
                progresso.getId().getAtividade().setFaturamento(Faturamento.FO);
            }
            getEntityManager().merge(progresso);
            getEntityManager().flush();
        }
        getEntityManager().getTransaction().commit();
    }

    public List<ProgressoAtividade> pegarProgressoAtividade(int data, TipoAtividade tipoAtividade, String idProjeto, String idModulo, String idPacote) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT pa FROM ProgressoAtividade pa WHERE YEAR(pa.id.atividade.previsaoInicio)= :paramData AND pa.id.tipoAtividade =:paramTipo ");
        sb.append(" AND pa.id.progresso =:paramProgresso ");
        if (!StringUtil.isEmpty(idProjeto)) {
            sb.append(" AND pa.id.atividade.pacote.modulo.projeto.id =:paramIdProjeto ");
        }
        if (!StringUtil.isEmpty(idModulo)) {
            sb.append(" AND pa.id.atividade.pacote.modulo.id =:paramIdModulo ");
        }
        if (!StringUtil.isEmpty(idPacote)) {
            sb.append(" AND pa.id.atividade.pacote.id =:paramIDPacote ");
        }
        sb.append(" group by pa.id.atividade");
        Query query = getEntityManager().createQuery(sb.toString());
        query.setParameter("paramData", data);
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
        entitys = query.getResultList();

        return entitys;
    }
    
    public List<ProgressoAtividade> pegarTodosNaoFaturados(String idProjeto, String idModulo, String idPacote) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT pa FROM ProgressoAtividade pa WHERE pa.faturamento NOT IN('FO')");
//        sb.append(" AND pa.id.progresso =:paramProgresso ");
        if (!StringUtil.isEmpty(idProjeto)) {
            sb.append(" AND pa.id.atividade.pacote.modulo.projeto.id =:paramIdProjeto ");
        }
        if (!StringUtil.isEmpty(idModulo)) {
            sb.append(" AND pa.id.atividade.pacote.modulo.id =:paramIdModulo ");
        }
        if (!StringUtil.isEmpty(idPacote)) {
            sb.append(" AND pa.id.atividade.pacote.id =:paramIDPacote ");
        }
        sb.append(" group by pa.id.atividade");
        Query query = getEntityManager().createQuery(sb.toString());
//        query.setParameter("paramData", data);
//        query.setParameter("paramProgresso", 100.0);

        if (!StringUtil.isEmpty(idProjeto)) {
            query.setParameter("paramIdProjeto", idProjeto);
        }
        if (!StringUtil.isEmpty(idModulo)) {
            query.setParameter("paramIdModulo", idModulo);
        }
        if (!StringUtil.isEmpty(idPacote)) {
            query.setParameter("paramIDPacote", idPacote);
        }
        entitys = query.getResultList();

        return entitys;
    }
    
    public List<ProgressoAtividade> faturadosEEmFaturamento(int data) {
        StringBuilder hql = new StringBuilder("SELECT pa FROM " + ProgressoAtividade.class.getName()).append(" pa WHERE YEAR(pa.id.atividade.fimAtividade)= :data");
        Query query = getEntityManager().createQuery(hql.toString());
        query.setParameter("data", data);
        return query.getResultList();
    }

}
