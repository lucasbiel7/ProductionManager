/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.database;

import br.com.stefanini.model.BaseEntity;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author lucas
 * @param <Entity>
 *
 */
public class GenericaDAO<Entity extends BaseEntity> {

    private final EntityManager entityManager;
    protected Entity entity;
    protected List<Entity> entitys;
    protected Class<Entity> classe;

    protected CriteriaBuilder criteriaBuilder;
    protected CriteriaQuery<Entity> criteriaQuery;
    protected Root<Entity> root;

    public GenericaDAO() {
        entityManager = Banco.getEntityManagerFactory().createEntityManager();
        classe = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(classe);
        root = criteriaQuery.from(classe);
    }

    public void salvar(Entity entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void editar(Entity entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void excluir(Entity entity) {
        entityManager.getTransaction().begin();
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Entity pegarPorId(Serializable id) {
        entity = entityManager.find(classe, id);
        entityManager.close();
        return entity;
    }

    public List<Entity> pegarTodos() {
        entitys = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return entitys;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
