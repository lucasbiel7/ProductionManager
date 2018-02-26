/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.stefanini.model.util;

import br.com.stefanini.model.entity.ProgressoAtividade;
import java.util.List;
import java.util.Date;

/**
 *
 * @author rkkitagawa
 */
public class PlanilhaDetalhes {
    
    private Date data;
    private List<ProgressoAtividade> lev;
    private List<ProgressoAtividade> dev;
    private List<ProgressoAtividade> tst;       
    private Double totalEstimadaContrato;
    private Double totalDetalhadaContrato;
    private Double totalEstimadaRepasse;
    private Double totalDetalhadaRepasse;    
    private boolean repasse;

    public PlanilhaDetalhes() {
    }

    public PlanilhaDetalhes(Date data, List<ProgressoAtividade> lev, List<ProgressoAtividade> dev, List<ProgressoAtividade> tst, String totalEstimadaContrato, String totalDetalhadaContrato, String totalEstimadaRepasse, String totalDetalhadaRepasse, boolean repasse) {
        this.data = data;
        this.lev = lev;
        this.dev = dev;
        this.tst = tst;
        this.totalEstimadaContrato = Double.parseDouble(totalEstimadaContrato.replace(".","").replace(",","."));
        this.totalDetalhadaContrato = Double.parseDouble(totalDetalhadaContrato.replace(".","").replace(",","."));
        this.totalEstimadaRepasse = Double.parseDouble(totalEstimadaRepasse.replace(".","").replace(",","."));
        this.totalDetalhadaRepasse = Double.parseDouble(totalDetalhadaRepasse.replace(".","").replace(",","."));
        this.repasse = repasse;
    }

    
    
    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the lev
     */
    public List<ProgressoAtividade> getLev() {
        return lev;
    }

    /**
     * @param lev the lev to set
     */
    public void setLev(List<ProgressoAtividade> lev) {
        this.lev = lev;
    }

    /**
     * @return the dev
     */
    public List<ProgressoAtividade> getDev() {
        return dev;
    }

    /**
     * @param dev the dev to set
     */
    public void setDev(List<ProgressoAtividade> dev) {
        this.dev = dev;
    }

    /**
     * @return the tst
     */
    public List<ProgressoAtividade> getTst() {
        return tst;
    }

    /**
     * @param tst the tst to set
     */
    public void setTst(List<ProgressoAtividade> tst) {
        this.tst = tst;
    }

    /**
     * @return the totalEstimadaContrato
     */
    public double getTotalEstimadaContrato() {
        return totalEstimadaContrato.doubleValue();
    }

    /**
     * @param totalEstimadaContrato the totalEstimadaContrato to set
     */
    public void setTotalEstimadaContrato(Double totalEstimadaContrato) {
        this.totalEstimadaContrato = totalEstimadaContrato;
    }

    /**
     * @return the totalDetalhadaContrato
     */
    public double getTotalDetalhadaContrato() {
        return totalDetalhadaContrato.doubleValue();
    }

    /**
     * @param totalDetalhadaContrato the totalDetalhadaContrato to set
     */
    public void setTotalDetalhadaContrato(Double totalDetalhadaContrato) {
        this.totalDetalhadaContrato = totalDetalhadaContrato;
    }

    /**
     * @return the totalEstimadaRepasse
     */
    public double getTotalEstimadaRepasse() {
        return totalEstimadaRepasse.doubleValue();
    }

    /**
     * @param totalEstimadaRepasse the totalEstimadaRepasse to set
     */
    public void setTotalEstimadaRepasse(Double totalEstimadaRepasse) {
        this.totalEstimadaRepasse = totalEstimadaRepasse;
    }

    /**
     * @return the totalDetalhadaRepasse
     */
    public double getTotalDetalhadaRepasse() {
        return totalDetalhadaRepasse.doubleValue();
    }

    /**
     * @param totalDetalhadaRepasse the totalDetalhadaRepasse to set
     */
    public void setTotalDetalhadaRepasse(Double totalDetalhadaRepasse) {
        this.totalDetalhadaRepasse = totalDetalhadaRepasse;
    }

    /**
     * @return the repasse
     */
    public boolean isRepasse() {
        return repasse;
    }

    /**
     * @param repasse the repasse to set
     */
    public void setRepasse(boolean repasse) {
        this.repasse = repasse;
    }

   
    
}
