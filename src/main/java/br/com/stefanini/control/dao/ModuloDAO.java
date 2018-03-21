/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.Projeto;
import java.util.List;

/**
 *
 * @author lucas
 */
public class ModuloDAO extends GenericaDAO<Modulo> {

    private static ModuloDAO moduloDAO;

    private ModuloDAO() {
        super();
    }

    public static ModuloDAO getInstance() {
        if (moduloDAO == null) {
            moduloDAO = new ModuloDAO();
        }
        moduloDAO.initConfiguration();
        return moduloDAO;
    }

    public List<Modulo> pegarPorProjeto(Projeto projeto) {
        criteriaQuery.where(criteriaBuilder.equal(root.get("projeto"), projeto));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();
        return entitys;
    }

}
