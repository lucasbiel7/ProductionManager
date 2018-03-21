/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.Pacote;
import java.util.List;

/**
 *
 * @author lucas
 */
public class PacoteDAO extends GenericaDAO<Pacote> {

    private PacoteDAO() {
        super();
    }

    private static PacoteDAO pacoteDAO;

    public static PacoteDAO getInstance() {
        if (pacoteDAO == null) {
            pacoteDAO = new PacoteDAO();
        }
        pacoteDAO.initConfiguration();
        return pacoteDAO;
    }

    public List<Pacote> pegarPorModulo(Modulo modulo) {
        criteriaQuery.where(criteriaBuilder.equal(root.get("modulo"), modulo));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();
        return entitys;
    }

}
