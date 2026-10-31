package com.example.model.tableperclass;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "electronique")
public class Electronique extends Produit {
    private int garantie;
    private String marque;

    public Electronique() { }

    public Electronique(String nom, BigDecimal prix, int stock, int garantie, String marque) {
        super(nom, prix, stock);
        this.garantie = garantie;
        this.marque = marque;
    }

    public int getGarantie() { return garantie; }
    public void setGarantie(int garantie) { this.garantie = garantie; }
    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    @Override
    public String toString() {
        return "Electronique{" + detailsCommuns() + ", garantie=" + garantie + ", marque=" + marque + '}';
    }
}
