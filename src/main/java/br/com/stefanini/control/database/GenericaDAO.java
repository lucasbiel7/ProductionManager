/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.database;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author lucas
 * @param <Entity>
 */
public class GenericaDAO<Entity> {

    protected Session session;
    protected Class<Entity> classe;
    protected Criteria criteria;
    protected Entity entity;
    protected List<Entity> entitys;

    public GenericaDAO() {
        classe = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        entitys = new ArrayList<>();
        session = Banco.getSessionFactory().openSession();
        criteria = session.createCriteria(classe);
    }

    public void cadastrar(Entity entity) {
        session.beginTransaction();
        session.save(entity);
        closeSession();
    }

    public void editar(Entity entity) {
        session.beginTransaction();
        session.update(entity);
        closeSession();
    }

    public void excluir(Entity entity) {
        session.beginTransaction();
        session.delete(entity);
        closeSession();
    }

    public Entity pegarPorId(Serializable id) {
        entity = (Entity) session.get(classe, id);
        closeSession();
        return entity;
    }

    public List<Entity> pegarTodos() {
        entitys = criteria.list();
        closeSession();
        return entitys;
    }

    private void closeSession() {
        if (session.isOpen()) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().commit();
            }
            session.flush();
            session.close();
        }
    }
}
