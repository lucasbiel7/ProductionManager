/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Usuario;
import javax.persistence.NoResultException;

/**
 *
 * @author lucas
 */
public class UsuarioDAO extends GenericaDAO<Usuario> {

    public Usuario login(String cpf, String password) {
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("pessoa").get("cpf"), cpf)),
                criteriaBuilder.equal(root.get("senha"), password)
        );
        try {
            return getEntityManager().createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
