
# Projet Arbre Généalogique en Java
harhour younes
belkezai mohamed wael
Ce projet implémente un système de gestion d'arbre généalogique en Java avec une visualisation graphique utilisant la bibliothèque Raylib.

## Structure du Projet

Le projet est organisé comme suit :

```
projet1_java/
├── famille/
│   ├── Personne.java           // Classe principale pour modéliser une personne
│   ├── TestPersonne.java       // Classe de test pour créer et afficher l'arbre
│   └── FamilyTreeVisualizer.java // Visualisation graphique avec Raylib
└── lib/
    └── jaylib.jar              // Bibliothèque Raylib pour Java
```

## Fonctionnalités

### Classe Personne
- Modélise une personne avec un nom, un âge et une liste d'enfants
- Permet d'ajouter des enfants et de naviguer dans l'arbre généalogique
- Implémente des méthodes pour déterminer les relations familiales (parent, grand-parent)
- Calcule le nombre total de descendants
- Affiche la descendance sous forme textuelle

### Visualisation Graphique
- Affiche l'arbre généalogique dans une fenêtre graphique
- Représente chaque personne par un rectangle contenant son nom et son âge
- Trace des lignes pour montrer les relations parent-enfant
- Dispose automatiquement les éléments pour une visualisation claire

## Exemple d'Arbre Généalogique

L'exemple implémenté représente la structure familiale suivante :

```
                Ahmed (89)
                /    |    \
               /     |     \
              /      |      \
    Fatima (70)  Mohamed (60)  Ali (66)
        |                      /    \
        |                     /      \
    Latifa (47)         Aicha (40)  Karim (38)
        |
        |
    Omar (21)
```

## Comment Exécuter le Projet

### Prérequis
- Java JDK 8 ou supérieur installé
- Les fichiers du projet dans le répertoire `projet1_java`

### Méthode 1 : Ligne de Commande

1. Ouvrir une invite de commande ou PowerShell
2. Naviguer vers le répertoire du projet
   ```
   cd chemin/vers/projet1_java
   ```
3. Compiler les fichiers Java
   ```
   javac -cp "lib/jaylib.jar" famille/*.java
   ```
4. Exécuter le programme
   ```
   java -cp "lib/jaylib.jar;." famille.TestPersonne
   ```
   
   Note: Sur macOS ou Linux, utiliser un deux-points au lieu d'un point-virgule:
   ```
   java -cp "lib/jaylib.jar:." famille.TestPersonne
   ```

### Méthode 2 : Utiliser un IDE (IntelliJ IDEA, Eclipse, etc.)

1. Ouvrir l'IDE
2. Ouvrir le dossier du projet (`projet1_java`)
3. Ajouter le JAR Jaylib comme bibliothèque:
   - Dans IntelliJ: File > Project Structure > Libraries > + > Java > Naviguer vers `lib/jaylib.jar`
   - Dans Eclipse: Clic droit sur le projet > Properties > Java Build Path > Libraries > Add External JARs > Naviguer vers `lib/jaylib.jar`
4. Exécuter la classe `famille.TestPersonne`

## Résultats Attendus

Lorsque vous exécutez le programme:

1. Une sortie texte dans la console affichant:
   - Des informations sur Ahmed (nombre d'enfants, descendants, etc.)
   - La structure de l'arbre généalogique au format texte

2. Une fenêtre graphique s'ouvre montrant:
   - La représentation visuelle de l'arbre généalogique
   - Chaque personne affichée dans un rectangle bleu avec son nom et son âge
   - Des lignes reliant les parents à leurs enfants

3. Vous pouvez fermer la visualisation en:
   - Appuyant sur la touche ESC
   - Cliquant sur le bouton de fermeture de la fenêtre

## Implémentation Technique

### Classe Personne
La classe `Personne` est le cœur du projet. Elle stocke les informations d'une personne et gère les relations familiales.

```java
public class Personne {
    private String nom;
    private int age;
    private ArrayList<Personne> enfants;
    // Attributs pour la visualisation
    private float x, y, width, height;
    
    // Constructeur, getters, setters et méthodes pour gérer les relations
    // ...
}
```

### Visualisation avec Raylib
La classe `FamilyTreeVisualizer` utilise la bibliothèque Raylib (via Jaylib) pour créer une représentation graphique de l'arbre généalogique:

```java
public class FamilyTreeVisualizer {
    // Initialise la fenêtre Raylib
    // Calcule les positions des personnes dans l'arbre
    // Dessine les personnes et les connexions
    // Gère la boucle principale de rendu
}
```

## Dépendances

- **Jaylib**: Une liaison Java pour Raylib, une bibliothèque simple pour le développement de jeux/graphiques.

## Remarques pour l'Évaluation

Ce projet démontre:
1. La programmation orientée objet en Java (encapsulation, héritage, etc.)
2. Les structures de données (ArrayList pour stocker les enfants)
3. Les algorithmes récursifs (pour parcourir l'arbre)
4. L'intégration avec une bibliothèque graphique externe
5. La disposition automatique d'éléments graphiques

## Dépannage

- Si vous obtenez une erreur "library not found", assurez-vous que le fichier JAR Jaylib est au bon endroit (`lib/jaylib.jar`)
- Si vous obtenez une erreur "class not found", assurez-vous d'avoir compilé tous les fichiers Java et inclus le répertoire courant dans le classpath
- Sur Windows, vous devrez peut-être utiliser des guillemets autour du classpath: `java -cp "lib/jaylib.jar;." famille.TestPersonne`
