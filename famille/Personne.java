package famille;

import java.util.ArrayList;

public class Personne {
    private String nom;
    private int age;
    private ArrayList<Personne> enfants;

    /**
     * Constructeur qui initialise une personne avec son nom et son âge
     * @param nom Le nom de la personne
     * @param age L'âge de la personne
     */
    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
        this.enfants = new ArrayList<>();
    }

    /**
     * Ajoute un enfant à la personne
     * @param enfant L'enfant à ajouter
     */
    public void ajouterEnfant(Personne enfant) {
        this.enfants.add(enfant);
    }

    /**
     * Retourne le nombre d'enfants de la personne
     * @return Le nombre d'enfants
     */
    public int getNombreEnfants() {
        return this.enfants.size();
    }

    /**
     * Détermine si la personne est un parent (a au moins un enfant)
     * @return true si la personne est un parent, false sinon
     */
    public boolean estParent() {
        return !this.enfants.isEmpty();
    }

    /**
     * Détermine si la personne est un grand-parent
     * (a au moins un enfant et un de ses enfants a au moins un enfant)
     * @return true si la personne est un grand-parent, false sinon
     */
    public boolean estGrandParent() {
        for (Personne enfant : this.enfants) {
            if (enfant.estParent()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne le nombre total de descendants (enfants, petits-enfants, etc.)
     * @return Le nombre total de descendants
     */
    public int getNombreDescendants() {
        int nombreTotal = this.enfants.size();
        
        for (Personne enfant : this.enfants) {
            nombreTotal += enfant.getNombreDescendants();
        }
        
        return nombreTotal;
    }

    /**
     * Affiche le détail de la descendance d'une personne
     */
    public void afficherDescendance() {
        afficherDescendance(0);
    }
    
    /**
     * Méthode auxiliaire pour afficher la descendance avec indentation
     * @param niveau Le niveau d'indentation
     */
    private void afficherDescendance(int niveau) {
        // Créer l'indentation en fonction du niveau
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < niveau; i++) {
            indentation.append("  ");
        }
        
        // Afficher le nom de la personne
        System.out.println(indentation + "- " + this.nom);
        
        // Afficher récursivement les enfants
        for (Personne enfant : this.enfants) {
            enfant.afficherDescendance(niveau + 1);
        }
    }
    
    /**
     * Getter pour le nom
     * @return Le nom de la personne
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Getter pour l'âge
     * @return L'âge de la personne
     */
    public int getAge() {
        return this.age;
    }
}
