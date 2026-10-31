package com.example.model.joined;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "employe")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salaire;

    protected Employe() { }

    protected Employe(String nom, String email, BigDecimal salaire) {
        this.nom = nom;
        this.email = email;
        this.salaire = salaire;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public BigDecimal getSalaire() { return salaire; }
    public void setSalaire(BigDecimal salaire) { this.salaire = salaire; }

    protected String detailsCommuns() {
        return "id=" + id + ", nom=" + nom + ", email=" + email + ", salaire=" + salaire;
    }
}
