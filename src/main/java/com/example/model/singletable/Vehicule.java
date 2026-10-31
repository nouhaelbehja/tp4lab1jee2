package com.example.model.singletable;

import javax.persistence.*;

@Entity
@Table(name = "vehicule")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicule_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String marque;
    @Column(nullable = false, unique = true)
    private String immatriculation;
    private int anneeFabrication;

    protected Vehicule() { }

    protected Vehicule(String marque, String immatriculation, int anneeFabrication) {
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.anneeFabrication = anneeFabrication;
    }

    public Long getId() { return id; }
    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }
    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public int getAnneeFabrication() { return anneeFabrication; }
    public void setAnneeFabrication(int anneeFabrication) { this.anneeFabrication = anneeFabrication; }

    protected String detailsCommuns() {
        return "id=" + id + ", marque=" + marque + ", immatriculation=" + immatriculation
                + ", anneeFabrication=" + anneeFabrication;
    }
}
