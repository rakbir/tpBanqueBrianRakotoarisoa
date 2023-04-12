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
import mg.itu.tpbanquebrianrakotoarisoa.ejb.GestionnaireCompte;
import mg.itu.tpbanquebrianrakotoarisoa.entities.CompteBancaire;

/**
 *
 * @author Brian Rakotoarisoa
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    
    public List<CompteBancaire> getAllComptes(){
        return gestionnaireCompte.getAllComptes();
    }
    
}
