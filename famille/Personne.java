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


    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
        this.enfants = new ArrayList<>();
    }

    public void ajouterEnfant(Personne enfant) {
        this.enfants.add(enfant);
    }


    public int getNombreEnfants() {
        return this.enfants.size();
    }


    public boolean estParent() {
        return !this.enfants.isEmpty();
    }


    public boolean estGrandParent() {
        for (Personne enfant : this.enfants) {
            if (enfant.estParent()) {
                return true;
            }
        }
        return false;
    }


    public int getNombreDescendants() {
        int nombreTotal = this.enfants.size();

        for (Personne enfant : this.enfants) {
            nombreTotal += enfant.getNombreDescendants();
        }

        return nombreTotal;
    }


    public void afficherDescendance() {
        afficherDescendance(0);
    }


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


    public String getNom() {
        return this.nom;
    }


    public int getAge() {
        return this.age;
    }


    public List<Personne> getEnfants() {
        return this.enfants;
    }


    public void setX(float x) {
        this.x = x;
    }


    public void setY(float y) {
        this.y = y;
    }


    public float getX() {
        return this.x;
    }


    public float getY() {
        return this.y;
    }


    public float getWidth() {
        return this.width;
    }


    public float getHeight() {
        return this.height;
    }


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
