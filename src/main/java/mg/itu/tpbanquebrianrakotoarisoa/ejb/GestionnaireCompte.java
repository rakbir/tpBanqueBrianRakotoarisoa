/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquebrianrakotoarisoa.ejb;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import mg.itu.tpbanquebrianrakotoarisoa.entities.CompteBancaire;
import mg.itu.tpbanquebrianrakotoarisoa.exception.ConcurrentAccessException;

/**
 *
 * @author Brian Rakotoarisoa
 */
@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",
    password="12345", 
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true"
    }
)
@Stateless
public class GestionnaireCompte {
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;
    
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }
    
    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }
    
    public Long nbComptes(){
        Query query = em.createNamedQuery("CompteBancaire.countAll");
        return (Long)query.getSingleResult();
    }
    
    public CompteBancaire findCompteById(Long id){
        return em.find(CompteBancaire.class, id);
    }
    
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
    
    public void transferer(CompteBancaire source, CompteBancaire destination, int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }
    
    /**
     * Dépôt d'argent sur un compte bancaire.
     * @param compte
     * @param montant 
     */
    public void deposer(CompteBancaire compte, int montant) {
        int solde = compte.getSolde();
        boolean concurrence = false;
        compte = em.find(CompteBancaire.class, compte.getId(),
                LockModeType.PESSIMISTIC_WRITE);
        if (solde != compte.getSolde()) {
            // le solde a été modifié ; il faut avertir l'utilisateur
            concurrence = true;
        }
        compte.deposer(montant);
        update(compte);
        if (concurrence) {
            // le solde a été modifié ; il faut avertir l'utilisateur
            throw new ConcurrentAccessException("Dépôt effectué mais le solde a été modifié concurremment");
        }
    }
    
    /**
     * Retrait d'argent sur un compte bancaire.
     * @param compte
     * @param montant 
     */
    public void retirer(CompteBancaire compte, int montant) {
        int solde= compte.getSolde();
        boolean concurrence = false;
        compte = em.find(CompteBancaire.class, compte.getId(),
                LockModeType.PESSIMISTIC_WRITE);
        if (solde != compte.getSolde()) {
            concurrence = true;
        }
        compte.retirer(montant);
        update(compte);
        if (concurrence) {
            throw new ConcurrentAccessException("Retrait effectué mais le solde a été modifié concurremment");
        }
    }
    
    public void supprimerCompte(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }
}
