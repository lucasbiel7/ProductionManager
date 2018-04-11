/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.control;

import br.com.stefanini.model.enuns.TipoPerfil;

/**
 *
 * @author rkkitagawa
 */
abstract class ControllerBase {
    public void validarPermissoes(TipoPerfil tipoPerfil){
        switch(tipoPerfil){
            case ANALISTA:
                buildAnalista();
                break;
            case BANCO_DADOS:
                buildBancoDados();
                break;
            case BDMG:
                buildBDMG();
                break;
            case DESENVOLVEDOR:
                buildDesenvolvedor();
                break;
                
            case GERENTE:
                buildGerente();
                break;
                
            case QUALIDADE:
                buildQualidade();
                break;
                
            case ADMINISTRADOR:
                buildAdministrador();
                break;
        }
    }
        
    abstract public void buildAnalista();
    abstract public void buildBancoDados();
    abstract public void buildBDMG();
    abstract public void buildDesenvolvedor();
    abstract public void buildGerente();
    abstract public void buildQualidade();
    abstract public void buildAdministrador();
    
}
