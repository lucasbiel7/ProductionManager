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
import java.util.Calendar;
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

    private static AtividadeDAO atividadeDAO;
    
    private AtividadeDAO(){
        super();
    }
    
    public static AtividadeDAO getInstance(){
        if (atividadeDAO==null) {
            atividadeDAO=new AtividadeDAO();
        }
        atividadeDAO.initConfiguration();
        return atividadeDAO;
    }
    
    public List<Atividade> pegarPorMes(Date data) {
        criteriaQuery.where(criteriaBuilder.equal(root.<java.sql.Date>get("previsaoInicio"), new java.sql.Date(data.getTime())));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();
        return entitys;
    }

    public void updateProximoMes(Date data){
        StringBuilder sb = new StringBuilder("SELECT a FROM " + Atividade.class.getName() + " a WHERE a.previsaoInicio= :data" );
        Query query = getEntityManager().createQuery(sb.toString());
        query.setParameter("data", data);
        List<Atividade> atividades = query.getResultList();

        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        Date dataProx = c.getTime();
        
        getEntityManager().getTransaction().begin();
        for(Atividade atv : atividades){
            Atividade a = new Atividade();
            a.setAliDetalhada(atv.getAliDetalhada());
            a.setAliEstimada(atv.getAliEstimada());
            a.setAtividadeArtefatos(new ArrayList<>());
            a.getAtividadeArtefatos().addAll(atv.getAtividadeArtefatos());
            a.setContagemDetalhada(atv.getContagemDetalhada());
            a.setContagemEstimada(atv.getContagemEstimada());
            a.setDescricao(atv.getDescricao());
            a.setFaturamento(atv.getFaturamento());
            a.setNomeAli(atv.getNomeAli());
            a.setOrdemServico(atv.getOrdemServico());
            a.setOrigemAtividade(atv.getOrigemAtividade());
            a.setPacote(atv.getPacote());
            a.setPrevisaoInicio(dataProx);
//            a.setProgresso();
            a.setProgressos(new ArrayList<>());
            a.getProgressos().addAll(atv.getProgressos());
            a.setSituacaoAtividade(atv.getSituacaoAtividade());
            a.setTpAtividade(atv.getTpAtividade());
            getEntityManager().persist(a);
            getEntityManager().flush();
        }
        getEntityManager().getTransaction().commit();
    }
    
    public List<Atividade> pegarPorAtividade(Atividade atividade, Date data) {
        List<Predicate> criterios = new ArrayList<>();
        criterios.add(criteriaBuilder.equal(root.<java.sql.Date>get("previsaoInicio"), new java.sql.Date(data.getTime())));
        
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
        
        return entitys;
    }
    
    public List<Atividade> buscarAtividade(String idProjeto, String idModulo, String idPacote, int data){
        
//        if(data==null){
//            return new ArrayList<>();
//        }
        StringBuilder hql = new StringBuilder("SELECT a FROM " + Atividade.class.getName()).append(" a WHERE YEAR(a.previsaoInicio)= :data");
        
        if(!StringUtil.isEmpty(idProjeto) && !StringUtil.isEmpty(idModulo) && !StringUtil.isEmpty(idPacote)){
            hql.append(" AND a.pacote.id = :idPacote AND a.pacote.modulo.id = :idModulo AND a.pacote.modulo.projeto.id = :idProjeto");
        }
        else if(!StringUtil.isEmpty(idModulo) && !StringUtil.isEmpty(idProjeto)) {
            hql.append(" AND a.pacote.modulo.id = :idModulo AND a.pacote.modulo.projeto.id = :idProjeto");
        }
        else if(!StringUtil.isEmpty(idProjeto)){
            hql.append(" AND a.pacote.modulo.projeto.id = :idProjeto");
        }
        
        Query query = getEntityManager().createQuery(hql.toString());
        
        if(data != 0){
            query.setParameter("data", data);
        }
        if(!StringUtil.isEmpty(idPacote)){
            query.setParameter("idPacote", idPacote);
        }
        if(!StringUtil.isEmpty(idModulo)){
            query.setParameter("idModulo", idModulo);
        }
        if(!StringUtil.isEmpty(idProjeto)){
            query.setParameter("idProjeto", idProjeto);
        }
        entitys=query.getResultList();
        
        return entitys;
    }

    public Atividade carregarArtefatos(Atividade atividade) {
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), atividade.getId()));
        root.fetch("atividadeArtefatos", JoinType.LEFT);
        entity = getEntityManager().createQuery(criteriaQuery).getSingleResult();
        
        return entity;
    }
}
