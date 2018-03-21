/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.ModificacaoAtividade;

/**
 *
 * @author lucas
 */
public class ModificacaoAtividadeDAO extends GenericaDAO<ModificacaoAtividade> {

    private static ModificacaoAtividadeDAO modificacaoAtividadeDAO;

    private ModificacaoAtividadeDAO() {
        super();
    }

    public static ModificacaoAtividadeDAO getInstance() {
        if (modificacaoAtividadeDAO == null) {
            modificacaoAtividadeDAO = new ModificacaoAtividadeDAO();
        }
        modificacaoAtividadeDAO.initConfiguration();
        return modificacaoAtividadeDAO;
    }

}
