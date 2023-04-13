/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquebrianrakotoarisoa.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import mg.itu.tpbanquebrianrakotoarisoa.ejb.GestionnaireCompte;
import mg.itu.tpbanquebrianrakotoarisoa.entities.CompteBancaire;
import mg.itu.tpbanquebrianrakotoarisoa.jsf.util.Util;

/**
 *
 * @author Brian Rakotoarisoa
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert implements Serializable{
    private long idSource;
    private long idDestination;
    private int somme;

    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    /**
    /**
     * Creates a new instance of Transfert
     */
    public Transfert() {
    }
    
    public void setIdSource(long sourceId){
        this.idSource = sourceId;
    }
    
    public long getIdSource(){
        return this.idSource;
    }
    
    public void setIdDestination(long destinationId){
        this.idDestination = destinationId;
    }
    
    public long getIdDestination(){
        return this.idDestination;
    }
    
    public int getSomme() {
        return somme;
    }
    
    public void setSomme(int somme) {
        this.somme = somme;
    }
    
    public String transfert(){
        boolean erreur = false;
        CompteBancaire source = this.gestionnaireCompte.findCompteById(idSource);
        CompteBancaire destination = this.gestionnaireCompte.findCompteById(idDestination);
        if (source == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < somme) { // à compléter pour le cas où le solde du compte source est insuffisant...
                Util.messageErreur("Solde du compte insuffisant!", "Solde insuffisant!", "form:somme");
                erreur = true;
            }
        }
        if (destination == null) {
             Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        }
        if(erreur){
            return null;
        }
        this.gestionnaireCompte.transferer(source, destination, this.somme);
        Util.addFlashInfoMessage("Le montant de "+this.somme+" a correctement été transféré depuis le compte de "+source.getNom()+" à celui de "+destination.getNom());
        return "listeComptes?faces-redirect=true";
    }
}
