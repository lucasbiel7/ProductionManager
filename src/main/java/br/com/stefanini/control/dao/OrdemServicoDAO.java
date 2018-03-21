/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.OrdemServico;

/**
 *
 * @author lucas
 */
public class OrdemServicoDAO extends GenericaDAO<OrdemServico> {

    private OrdemServicoDAO() {
        super();
    }

    private static OrdemServicoDAO ordemServicoDAO;

    public static OrdemServicoDAO getInstance() {
        if (ordemServicoDAO == null) {
            ordemServicoDAO = new OrdemServicoDAO();
        }
        ordemServicoDAO.initConfiguration();
        return ordemServicoDAO;
    }

}
