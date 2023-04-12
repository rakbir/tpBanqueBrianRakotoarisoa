/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquebrianrakotoarisoa.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mg.itu.tpbanquebrianrakotoarisoa.entities.CompteBancaire;

/**
 *
 * @author Brian Rakotoarisoa
 */
@Singleton
@Startup
public class Init {
    @EJB
    private GestionnaireCompte gestionnaireCompteEjb;
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void creerComptes(){
        Long compte = gestionnaireCompteEjb.nbComptes();
        if(compte == 0){
            gestionnaireCompteEjb.creerCompte(new CompteBancaire("John Lennon", 150000));
            gestionnaireCompteEjb.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            gestionnaireCompteEjb.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            gestionnaireCompteEjb.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }
    }
}
