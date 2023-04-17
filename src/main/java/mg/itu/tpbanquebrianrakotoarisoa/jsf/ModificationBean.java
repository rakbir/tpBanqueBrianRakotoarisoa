/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquebrianrakotoarisoa.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import mg.itu.tpbanquebrianrakotoarisoa.ejb.GestionnaireCompte;
import mg.itu.tpbanquebrianrakotoarisoa.entities.CompteBancaire;
import mg.itu.tpbanquebrianrakotoarisoa.jsf.util.Util;

/**
 *
 * @author Brian Rakotoarisoa
 */
@Named(value = "modificationBean")
@ViewScoped
public class ModificationBean implements Serializable{
    private long id;

    private CompteBancaire compte;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    /**
     * Creates a new instance of ModificationBean
     */
    public ModificationBean() {
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }
    
    public void loadCompte(){
        this.compte = gestionnaireCompte.findCompteById(id);
    }
    
    public String enregistrerModification(){
        gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage("Modifications enregistrées, compte: "+id+", nom: "+compte.getNom());
        return "listeComptes?faces-redirect=true";
    }
}
