package com.example.model.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MOTO")
public class Moto extends Vehicule {
    private boolean sidecar;
    private int cylindree;

    public Moto() { }

    public Moto(String marque, String immatriculation, int anneeFabrication,
                boolean sidecar, int cylindree) {
        super(marque, immatriculation, anneeFabrication);
        this.sidecar = sidecar;
        this.cylindree = cylindree;
    }

    public boolean isSidecar() { return sidecar; }
    public void setSidecar(boolean sidecar) { this.sidecar = sidecar; }
    public int getCylindree() { return cylindree; }
    public void setCylindree(int cylindree) { this.cylindree = cylindree; }

    @Override
    public String toString() {
        return "Moto{" + detailsCommuns() + ", sidecar=" + sidecar + ", cylindree=" + cylindree + '}';
    }
}
