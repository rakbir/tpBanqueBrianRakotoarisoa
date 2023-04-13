/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquebrianrakotoarisoa.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import mg.itu.tpbanquebrianrakotoarisoa.ejb.GestionnaireCompte;
import mg.itu.tpbanquebrianrakotoarisoa.entities.CompteBancaire;

/**
 *
 * @author Brian Rakotoarisoa
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    private List<CompteBancaire> allComptes;
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    
    public List<CompteBancaire> getAllComptes(){
        if(this.allComptes == null){
            this.allComptes = gestionnaireCompte.getAllComptes();
        }
        return this.allComptes;
    }
    
    /**
     * Si le solde est supérieur ou égal au filtre, donc true
     * solde = la valeur de la colonne à laquelle appliquer le filtre
     * filtre = la valeur du filtre à appliquer 
     * locale = si application de formattages géographiques
     */
    public boolean filterBySolde(int solde, String filtre, Locale locale){
        return solde >= Integer.valueOf(filtre) ? true : false;
    }
    
}
