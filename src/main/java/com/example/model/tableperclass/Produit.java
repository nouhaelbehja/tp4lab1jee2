package com.example.model.tableperclass;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produit_sequence")
    @SequenceGenerator(name = "produit_sequence", sequenceName = "produit_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal prix;
    private int stock;

    protected Produit() { }

    protected Produit(String nom, BigDecimal prix, int stock) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    protected String detailsCommuns() {
        return "id=" + id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock;
    }
}
