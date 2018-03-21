/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Usuario;
import br.com.stefanini.model.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

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
            entity=getEntityManager().createQuery(criteriaQuery).getSingleResult();
            getEntityManager().close();
            return entity;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario pegarAtuacoes(Usuario usuario) {
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), usuario.getId()));
        root.fetch("atuando", JoinType.LEFT);
        entity = getEntityManager().createQuery(criteriaQuery).getSingleResult();
        getEntityManager().close();
        return entity;
    }

    public List<Usuario> pesquisarUsuario(Usuario usuario) {
        List<Predicate> criterios = new ArrayList<>();
        if (usuario.getPerfil() != null) {
            criterios.add(criteriaBuilder.equal(root.get("perfil"), usuario.getPerfil()));
        }
        if (!StringUtil.isEmpty(usuario.getPessoa().getCpf().replaceAll("[.-]", "").trim())) {
            criterios.add(criteriaBuilder.equal(root.get("pessoa").get("cpf"), usuario.getPessoa().getCpf()));
        }
        if (!StringUtil.isEmpty(usuario.getPessoa().getNome())) {
            criterios.add(criteriaBuilder.like(root.get("pessoa").get("nome"), usuario.getPessoa().getNome() + "%"));
        }
        if (!StringUtil.isEmpty(usuario.getPessoa().getEmail())) {
            criterios.add(criteriaBuilder.like(root.get("pessoa").get("email"), usuario.getPessoa().getEmail() + "%"));
        }
        criteriaQuery.where(criteriaBuilder.and(criterios.toArray(new Predicate[]{})));
        entitys = getEntityManager().createQuery(criteriaQuery).getResultList();
        getEntityManager().close();
        return entitys;
    }

}
