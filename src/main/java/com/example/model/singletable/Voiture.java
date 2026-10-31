package com.example.model.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VOITURE")
public class Voiture extends Vehicule {
    private int nombrePortes;
    private String carburant;

    public Voiture() { }

    public Voiture(String marque, String immatriculation, int anneeFabrication,
                   int nombrePortes, String carburant) {
        super(marque, immatriculation, anneeFabrication);
        this.nombrePortes = nombrePortes;
        this.carburant = carburant;
    }

    public int getNombrePortes() { return nombrePortes; }
    public void setNombrePortes(int nombrePortes) { this.nombrePortes = nombrePortes; }
    public String getCarburant() { return carburant; }
    public void setCarburant(String carburant) { this.carburant = carburant; }

    @Override
    public String toString() {
        return "Voiture{" + detailsCommuns() + ", nombrePortes=" + nombrePortes
                + ", carburant=" + carburant + '}';
    }
}
