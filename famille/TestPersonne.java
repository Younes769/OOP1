package famille;

public class TestPersonne {
    public static void main(String[] args) {
        // Création des personnes selon la hiérarchie de l'image
        Personne ahmed = new Personne("Ahmed", 89);

        // Enfants d'Ahmed
        Personne fatima = new Personne("Fatima", 70);
        Personne mohamed = new Personne("Mohamed", 60);
        Personne ali = new Personne("Ali", 66);

        // Enfants de Fatima
        Personne latifa = new Personne("Latifa", 47);

        // Enfants de Latifa
        Personne omar = new Personne("Omar", 21);

        // Enfants d'Ali
        Personne aicha = new Personne("Aicha", 40);
        Personne karim = new Personne("Karim", 38);

        // Construction de la hiérarchie





        // Tests textuels
        System.out.println("=== Informations sur " + ahmed.getNom() + " ===");
        System.out.println("Nombre d'enfants: " + ahmed.getNombreEnfants());
        System.out.println("Est parent: " + ahmed.estParent());
        System.out.println("Est grand-parent: " + ahmed.estGrandParent());
        System.out.println("Nombre total de descendants: " + ahmed.getNombreDescendants());

        System.out.println("\n=== Descendance de " + ahmed.getNom() + " ===");
        ahmed.afficherDescendance();

        // Lancer la visualisation graphique
        FamilyTreeVisualizer visualizer = new FamilyTreeVisualizer(ahmed);
        visualizer.run();
    }
}
