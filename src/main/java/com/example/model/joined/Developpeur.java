package com.example.model.joined;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "developpeur")
public class Developpeur extends Employe {
    private String langage;
    private String niveau;

    public Developpeur() { }

    public Developpeur(String nom, String email, BigDecimal salaire, String langage, String niveau) {
        super(nom, email, salaire);
        this.langage = langage;
        this.niveau = niveau;
    }

    public String getLangage() { return langage; }
    public void setLangage(String langage) { this.langage = langage; }
    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    @Override
    public String toString() {
        return "Developpeur{" + detailsCommuns() + ", langage=" + langage + ", niveau=" + niveau + '}';
    }
}
