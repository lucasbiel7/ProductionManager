/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control.dao;

import br.com.stefanini.control.database.GenericaDAO;
import br.com.stefanini.model.entity.Perfil;

/**
 *
 * @author lucas
 */
public class PerfilDAO extends GenericaDAO<Perfil> {

    private static PerfilDAO perfilDAO;

    private PerfilDAO() {
        super();
    }

    public static PerfilDAO getInstance() {
        if (perfilDAO == null) {
            perfilDAO = new PerfilDAO();
        }
        perfilDAO.initConfiguration();
        return perfilDAO;
    }
}
