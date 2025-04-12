package famille;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;
import com.raylib.Raylib.Vector2;
import com.raylib.Raylib.Rectangle;
import com.raylib.Raylib.Color;

/**
 * Classe pour visualiser un arbre généalogique avec Raylib
 */
public class FamilyTreeVisualizer {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 800;
    private static final float HORIZONTAL_SPACING = 40;
    private static final float VERTICAL_SPACING = 80;

    private Personne racine;
    private boolean initialized = false;

    /**
     * Constructeur
     * @param racine La personne racine de l'arbre généalogique
     */
    public FamilyTreeVisualizer(Personne racine) {
        this.racine = racine;
    }

    /**
     * Initialise la fenêtre Raylib et calcule les positions
     */
    public void initialize() {
        if (!initialized) {
            // Initialiser la fenêtre Raylib
            InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Arbre Généalogique");
            SetTargetFPS(60);

            // Calculer les positions des personnes dans l'arbre
            racine.calculatePositions(SCREEN_WIDTH / 2, 50, HORIZONTAL_SPACING, VERTICAL_SPACING);

            initialized = true;
        }
    }

    /**
     * Dessine une personne avec son nom et son âge
     * @param personne La personne à dessiner
     */
    private void drawPerson(Personne personne) {
        float x = personne.getX();
        float y = personne.getY();
        float width = personne.getWidth();
        float height = personne.getHeight();

        // Dessiner le rectangle de la personne
        DrawRectangleRounded(new Rectangle().x(x).y(y).width(width).height(height), 0.2f, 10, SKYBLUE);
        DrawRectangleRoundedLines(new Rectangle().x(x).y(y).width(width).height(height), 0.2f, 10, DARKBLUE);

        // Dessiner le nom et l'âge
        String info = personne.getNom() + " " + personne.getAge();
        float textWidth = MeasureText(info, 20);
        DrawText(info, (int)(x + width/2 - textWidth/2), (int)(y + height/2 - 10), 20, DARKBLUE);
    }

    /**
     * Dessine une ligne entre un parent et son enfant
     * @param parent Le parent
     * @param enfant L'enfant
     */
    private void drawConnection(Personne parent, Personne enfant) {
        float startX = parent.getX() + parent.getWidth() / 2;
        float startY = parent.getY() + parent.getHeight();
        float endX = enfant.getX() + enfant.getWidth() / 2;
        float endY = enfant.getY();

        DrawLine((int)startX, (int)startY, (int)endX, (int)endY, DARKBLUE);
    }

    /**
     * Dessine récursivement l'arbre généalogique
     * @param personne La personne racine
     */
    private void drawFamilyTree(Personne personne) {
        // Dessiner d'abord les connexions
        for (Personne enfant : personne.getEnfants()) {
            drawConnection(personne, enfant);
            drawFamilyTree(enfant);
        }

        // Puis dessiner la personne (pour qu'elle soit au-dessus des lignes)
        drawPerson(personne);
    }

    /**
     * Exécute la boucle principale de visualisation
     */
    public void run() {
        initialize();

        // Boucle principale
        while (!WindowShouldClose()) {
            // Mise à jour

            // Dessin
            BeginDrawing();
            ClearBackground(RAYWHITE);

            drawFamilyTree(racine);

            DrawText("Arbre Généalogique", 20, 20, 30, DARKGRAY);
            DrawText("Appuyez sur ESC pour quitter", 20, SCREEN_HEIGHT - 40, 20, DARKGRAY);

            EndDrawing();
        }

        // Fermer la fenêtre
        CloseWindow();
    }
}
