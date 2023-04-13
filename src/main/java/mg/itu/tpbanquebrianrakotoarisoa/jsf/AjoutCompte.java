/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquebrianrakotoarisoa.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanquebrianrakotoarisoa.ejb.GestionnaireCompte;
import mg.itu.tpbanquebrianrakotoarisoa.entities.CompteBancaire;
import mg.itu.tpbanquebrianrakotoarisoa.jsf.util.Util;

/**
 *
 * @author Brian Rakotoarisoa
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {
    private String nom;
    
    @PositiveOrZero
    private int solde;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }
    
        public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getSolde() {
        return solde;
    }
    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String ajouterCompte(){
        CompteBancaire nouveau = new CompteBancaire(this.nom, this.solde);
        gestionnaireCompte.creerCompte(nouveau);
        Util.addFlashInfoMessage("Le compte de "+nouveau.getNom()+" est correctement créé avec un solde de "+nouveau.getSolde());
        return "ajoutCompte?faces-redirect=true";
    }
}
