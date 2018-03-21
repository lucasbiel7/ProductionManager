/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Atuacao;

/**
 *
 * @author lucas
 */
public class AtuacaoDAO extends GenericaDAO<Atuacao> {

    private static AtuacaoDAO atuacaoDAO;

    private AtuacaoDAO() {
        super();
    }

    public static AtuacaoDAO getInstance() {
        if (atuacaoDAO == null) {
            atuacaoDAO = new AtuacaoDAO();
        }
        atuacaoDAO.initConfiguration();
        return atuacaoDAO;
    }

}
