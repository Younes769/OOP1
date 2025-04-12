package famille;

public class TestPersonne {
    public static void main(String[] args) {
        // Création des personnes selon la hiérarchie
        Personne ahmed = new Personne("Ahmed", 70);
        
        // Enfants d'Ahmed
        Personne mohamed = new Personne("Mohamed", 45);
        Personne fatima = new Personne("Fatima", 42);
        Personne karim = new Personne("Karim", 40);
        
        // Petits-enfants d'Ahmed (enfants de Mohamed)
        Personne ali = new Personne("Ali", 20);
        Personne nadia = new Personne("Nadia", 18);
        
        // Petits-enfants d'Ahmed (enfants de Fatima)
        Personne samira = new Personne("Samira", 15);
        
        // Petits-enfants d'Ahmed (enfants de Karim)
        Personne youssef = new Personne("Youssef", 12);
        Personne leila = new Personne("Leila", 10);
        
        // Construction de la hiérarchie
        ahmed.ajouterEnfant(mohamed);
        ahmed.ajouterEnfant(fatima);
        ahmed.ajouterEnfant(karim);
        
        mohamed.ajouterEnfant(ali);
        mohamed.ajouterEnfant(nadia);
        
        fatima.ajouterEnfant(samira);
        
        karim.ajouterEnfant(youssef);
        karim.ajouterEnfant(leila);
        
        // Tests
        System.out.println("=== Informations sur " + ahmed.getNom() + " ===");
        System.out.println("Nombre d'enfants: " + ahmed.getNombreEnfants());
        System.out.println("Est parent: " + ahmed.estParent());
        System.out.println("Est grand-parent: " + ahmed.estGrandParent());
        System.out.println("Nombre total de descendants: " + ahmed.getNombreDescendants());
        
        System.out.println("\n=== Descendance de " + ahmed.getNom() + " ===");
        ahmed.afficherDescendance();
    }
}
