package com.example.model.joined;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager extends Employe {
    private int nombreEquipes;
    private String departement;

    public Manager() { }

    public Manager(String nom, String email, BigDecimal salaire, int nombreEquipes, String departement) {
        super(nom, email, salaire);
        this.nombreEquipes = nombreEquipes;
        this.departement = departement;
    }

    public int getNombreEquipes() { return nombreEquipes; }
    public void setNombreEquipes(int nombreEquipes) { this.nombreEquipes = nombreEquipes; }
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }

    @Override
    public String toString() {
        return "Manager{" + detailsCommuns() + ", nombreEquipes=" + nombreEquipes
                + ", departement=" + departement + '}';
    }
}
