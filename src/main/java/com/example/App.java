package com.example;

import com.example.model.joined.*;
import com.example.model.singletable.*;
import com.example.model.tableperclass.*;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-inheritance");
        try {
            EntityManager em = emf.createEntityManager();
            try {
                demonstrer(em);
            } finally {
                try {
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                } finally {
                    em.close();
                }
            }
        } finally {
            emf.close();
        }
    }

    private static void demonstrer(EntityManager em) {
        Voiture voiture = new Voiture("Toyota", "12345-A-6", 2023, 4, "Hybride");
        Moto moto = new Moto("Harley", "67890-B-6", 2022, true, 1200);
        Developpeur dev = new Developpeur("Salma", "salma@example.com",
                new BigDecimal("12500.00"), "Java", "Confirme");
        Manager manager = new Manager("Yassine", "yassine@example.com",
                new BigDecimal("18000.00"), 3, "Informatique");
        Livre livre = new Livre("Clean Code", new BigDecimal("320.00"), 12,
                "Robert C. Martin", "9780132350884");
        Electronique electronique = new Electronique("Smartphone", new BigDecimal("4500.00"),
                8, 24, "Samsung");

        System.out.println("=== CREATION ===");
        em.getTransaction().begin();
        em.persist(voiture);
        em.persist(moto);
        em.persist(dev);
        em.persist(manager);
        em.persist(livre);
        em.persist(electronique);
        em.getTransaction().commit();

        Long voitureId = voiture.getId();
        Long motoId = moto.getId();
        Long devId = dev.getId();
        Long managerId = manager.getId();
        Long livreId = livre.getId();
        Long electroniqueId = electronique.getId();

        // Force les lectures suivantes a recuperer les donnees en base.
        em.clear();
        System.out.println("=== LECTURE POLYMORPHIQUE ===");
        afficher(em, Vehicule.class, 2);
        afficher(em, Employe.class, 2);
        afficher(em, Produit.class, 2);
        Voiture voitureLue = em.find(Voiture.class, voitureId);
        Moto motoLue = em.find(Moto.class, motoId);
        Developpeur devLu = em.find(Developpeur.class, devId);
        Manager managerLu = em.find(Manager.class, managerId);
        Livre livreLu = em.find(Livre.class, livreId);
        Electronique electroniqueLue = em.find(Electronique.class, electroniqueId);
        verifier(voitureLue != null && motoLue != null && devLu != null
                && managerLu != null && livreLu != null && electroniqueLue != null,
                "Les six sous-classes sont rechargees");
        verifier("12345-A-6".equals(voitureLue.getImmatriculation())
                && voitureLue.getAnneeFabrication() == 2023
                && "Hybride".equals(voitureLue.getCarburant())
                && motoLue.getCylindree() == 1200
                && "salma@example.com".equals(devLu.getEmail())
                && devLu.getSalaire().compareTo(new BigDecimal("12500.00")) == 0
                && "Confirme".equals(devLu.getNiveau())
                && "Informatique".equals(managerLu.getDepartement())
                && livreLu.getPrix().compareTo(new BigDecimal("320.00")) == 0
                && livreLu.getStock() == 12
                && "9780132350884".equals(livreLu.getIsbn())
                && "Samsung".equals(electroniqueLue.getMarque()),
                "Les champs supplementaires sont conserves en base");

        System.out.println("=== MODIFICATION ===");
        em.getTransaction().begin();
        voitureLue.setCarburant("Essence");
        motoLue.setCylindree(1250);
        devLu.setNiveau("Senior");
        managerLu.setDepartement("Recherche et developpement");
        livreLu.setStock(10);
        electroniqueLue.setPrix(new BigDecimal("4200.00"));
        // Les objets sont geres : Hibernate detecte leurs modifications au commit.
        em.getTransaction().commit();
        em.clear();
        verifier("Essence".equals(em.find(Voiture.class, voitureId).getCarburant())
                && em.find(Moto.class, motoId).getCylindree() == 1250
                && "Senior".equals(em.find(Developpeur.class, devId).getNiveau())
                && "Recherche et developpement".equals(em.find(Manager.class, managerId).getDepartement())
                && em.find(Livre.class, livreId).getStock() == 10
                && em.find(Electronique.class, electroniqueId).getPrix()
                    .compareTo(new BigDecimal("4200.00")) == 0,
                "Les modifications des six sous-classes sont relues en base");
        afficher(em, Vehicule.class, 2);
        afficher(em, Employe.class, 2);
        afficher(em, Produit.class, 2);

        System.out.println("=== SUPPRESSION ===");
        em.getTransaction().begin();
        em.remove(em.find(Voiture.class, voitureId));
        em.remove(em.find(Moto.class, motoId));
        em.remove(em.find(Developpeur.class, devId));
        em.remove(em.find(Manager.class, managerId));
        em.remove(em.find(Livre.class, livreId));
        em.remove(em.find(Electronique.class, electroniqueId));
        em.getTransaction().commit();
        em.clear();
        verifier(em.find(Voiture.class, voitureId) == null
                && em.find(Moto.class, motoId) == null
                && em.find(Developpeur.class, devId) == null
                && em.find(Manager.class, managerId) == null
                && em.find(Livre.class, livreId) == null
                && em.find(Electronique.class, electroniqueId) == null,
                "Les six entites ont ete supprimees");
        afficher(em, Vehicule.class, 0);
        afficher(em, Employe.class, 0);
        afficher(em, Produit.class, 0);
        System.out.println("=== VERIFICATIONS CRUD REUSSIES ===");
    }

    private static <T> void afficher(EntityManager em, Class<T> type, int nombreAttendu) {
        List<T> resultats = em.createQuery("SELECT e FROM " + type.getSimpleName()
                + " e ORDER BY e.id", type).getResultList();
        System.out.println("--- " + type.getSimpleName() + " : " + resultats.size() + " ---");
        resultats.forEach(System.out::println);
        verifier(resultats.size() == nombreAttendu,
                type.getSimpleName() + " : " + nombreAttendu + " resultat(s)");
    }

    private static void verifier(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException("ECHEC : " + message);
        }
        System.out.println("[OK] " + message);
    }
}
