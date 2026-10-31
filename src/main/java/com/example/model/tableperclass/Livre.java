package com.example.model.tableperclass;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "livre")
public class Livre extends Produit {
    private String auteur;
    @Column(nullable = false, unique = true, length = 17)
    private String isbn;

    public Livre() { }

    public Livre(String nom, BigDecimal prix, int stock, String auteur, String isbn) {
        super(nom, prix, stock);
        this.auteur = auteur;
        this.isbn = isbn;
    }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public String toString() {
        return "Livre{" + detailsCommuns() + ", auteur=" + auteur + ", isbn=" + isbn + '}';
    }
}
