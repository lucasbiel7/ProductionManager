/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Modulo;
import br.com.stefanini.model.entity.Pacote;
import br.com.stefanini.model.entity.Projeto;
import java.util.List;

/**
 *
 * @author lucas
 */
public class PacoteDAO extends GenericaDAO<Pacote> {

     public List<Pacote> pegarPorModulo(Modulo modulo) {
        criteriaQuery.where(criteriaBuilder.equal(root.get("modulo"), modulo));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();
        getEntityManager().close();
        return entitys;
    }
}
