/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Versao;
import javax.persistence.Query;

/**
 *
 * @author higo
 */
public class VersaoDAO extends GenericaDAO<Versao> {
    private static VersaoDAO versaoDAO;

    private VersaoDAO() {
        super();
    }

    public static VersaoDAO getInstance() {
        if (versaoDAO == null) {
            versaoDAO = new VersaoDAO();
        }
        versaoDAO.initConfiguration();
        return versaoDAO;
    }
    
    public Versao getVersaoAtual(){
        String hql = "SELECT v FROM " + Versao.class.getName() + " v ORDER BY v.dtInclusao DESC";
        Query query = getEntityManager().createQuery(hql);
        query.setMaxResults(1);
        return (Versao) query.getSingleResult();
    }
}
