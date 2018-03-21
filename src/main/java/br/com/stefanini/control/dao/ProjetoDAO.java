/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Projeto;

/**
 *
 * @author lucas
 */
public class ProjetoDAO extends GenericaDAO<Projeto> {

    private static ProjetoDAO projetoDAO;

    private ProjetoDAO() {
        super();
    }

    public static ProjetoDAO getInstance() {
        if (projetoDAO == null) {
            projetoDAO = new ProjetoDAO();
        }
        projetoDAO.initConfiguration();
        return projetoDAO;
    }
}