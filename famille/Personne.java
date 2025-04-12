package famille;

import java.util.ArrayList;
import java.util.List;

public class Personne {
    private String nom;
    private int age;
    private ArrayList<Personne> enfants;

    // Attributs pour la visualisation
    private float x;
    private float y;
    private float width = 120;
    private float height = 60;

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

    /**
     * Getter pour la liste des enfants
     * @return La liste des enfants
     */
    public List<Personne> getEnfants() {
        return this.enfants;
    }

    /**
     * Définit la position X pour l'affichage
     * @param x La position X
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Définit la position Y pour l'affichage
     * @param y La position Y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Retourne la position X
     * @return La position X
     */
    public float getX() {
        return this.x;
    }

    /**
     * Retourne la position Y
     * @return La position Y
     */
    public float getY() {
        return this.y;
    }

    /**
     * Retourne la largeur du rectangle représentant la personne
     * @return La largeur
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Retourne la hauteur du rectangle représentant la personne
     * @return La hauteur
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Calcule et définit les positions pour cette personne et tous ses descendants
     * @param startX Position X de départ
     * @param startY Position Y de départ
     * @param horizontalSpacing Espacement horizontal entre les personnes
     * @param verticalSpacing Espacement vertical entre les niveaux
     * @return La largeur totale occupée par cette personne et ses descendants
     */
    public float calculatePositions(float startX, float startY, float horizontalSpacing, float verticalSpacing) {
        this.y = startY;

        if (enfants.isEmpty()) {
            // Si pas d'enfants, position simple
            this.x = startX;
            return width;
        }

        // Calculer les positions des enfants
        float childrenTotalWidth = 0;
        float childY = startY + height + verticalSpacing;

        // Premier passage pour calculer la largeur totale
        for (Personne enfant : enfants) {
            childrenTotalWidth += enfant.calculatePositions(0, childY, horizontalSpacing, verticalSpacing);
            if (enfant != enfants.get(enfants.size() - 1)) {
                childrenTotalWidth += horizontalSpacing;
            }
        }

        // Deuxième passage pour positionner les enfants avec la largeur calculée
        float childX = startX - (childrenTotalWidth / 2) + (width / 2);
        for (Personne enfant : enfants) {
            float childWidth = enfant.calculatePositions(childX, childY, horizontalSpacing, verticalSpacing);
            childX += childWidth + horizontalSpacing;
        }

        // Centrer cette personne au-dessus de ses enfants
        if (childrenTotalWidth > width) {
            this.x = startX - (childrenTotalWidth - width) / 2;
            return childrenTotalWidth;
        } else {
            this.x = startX;
            return width;
        }
    }
}
