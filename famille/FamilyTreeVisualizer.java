package famille;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;
import com.raylib.Raylib.Vector2;
import com.raylib.Raylib.Rectangle;

import java.util.ArrayList;

public class FamilyTreeVisualizer {
    private static final int SCREEN_WIDTH = 1200;
    private static final int SCREEN_HEIGHT = 800;
    private static final float HORIZONTAL_SPACING = 40;
    private static final float VERTICAL_SPACING = 80;

    private Personne racine;
    private boolean initialized = false;
    private Personne selectedPerson = null;

    private String inputName = "";
    private String inputAgeStr = "0";
    private boolean showInputBox = false;

    public FamilyTreeVisualizer(Personne racine) {
        this.racine = racine;
    }

    public void initialize() {
        if (!initialized) {
            InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Arbre Généalogique");
            SetTargetFPS(60);
            racine.calculatePositions(SCREEN_WIDTH / 2, 50, HORIZONTAL_SPACING, VERTICAL_SPACING);
            initialized = true;
        }
    }

    private void drawPerson(Personne personne) {
        float x = personne.getX();
        float y = personne.getY();
        float width = personne.getWidth();
        float height = personne.getHeight();

        DrawRectangleRounded(new Rectangle().x(x).y(y).width(width).height(height), 0.2f, 10, SKYBLUE);
        DrawRectangleRoundedLines(new Rectangle().x(x).y(y).width(width).height(height), 0.2f, 10, DARKBLUE);

        String info = personne.getNom() + " " + personne.getAge();
        float textWidth = MeasureText(info, 20);
        DrawText(info, (int)(x + width / 2 - textWidth / 2), (int)(y + height / 2 - 10), 20, DARKBLUE);
    }

    private void drawConnection(Personne parent, Personne enfant) {
        float startX = parent.getX() + parent.getWidth() / 2;
        float startY = parent.getY() + parent.getHeight();
        float endX = enfant.getX() + enfant.getWidth() / 2;
        float endY = enfant.getY();

        DrawLine((int)startX, (int)startY, (int)endX, (int)endY, DARKBLUE);
    }

    private void drawFamilyTree(Personne personne) {
        for (Personne enfant : personne.getEnfants()) {
            drawConnection(personne, enfant);
            drawFamilyTree(enfant);
        }
        drawPerson(personne);
    }

    private void displayInputBox() {
        if (showInputBox) {
            int boxX = SCREEN_WIDTH / 4;
            int boxY = SCREEN_HEIGHT / 2;
            int boxW = SCREEN_WIDTH / 2;
            int boxH = 130;

            DrawRectangle(boxX, boxY, boxW, boxH, LIGHTGRAY);
            DrawText("Enter child's name:", boxX + 10, boxY + 10, 20, DARKGRAY);
            DrawText(inputName, boxX + 10, boxY + 35, 20, DARKBLUE);

            DrawText("Enter child's age:", boxX + 10, boxY + 65, 20, DARKGRAY);
            DrawText(inputAgeStr, boxX + 220, boxY + 65, 20, DARKBLUE);

            DrawText("Press ENTER to add, ESC to cancel", boxX + 10, boxY + 100, 20, DARKGRAY);
        }
    }

    public void run() {
        initialize();

        while (!WindowShouldClose()) {
            if (showInputBox) {
                // Confirm addition
                if (IsKeyPressed(KEY_ENTER) && selectedPerson != null && !inputName.isEmpty()) {
                    try {
                        int inputAge = Integer.parseInt(inputAgeStr);
                        Personne newChild = new Personne(inputName, inputAge);
                        selectedPerson.ajouterEnfant(newChild);
                        racine.calculatePositions(SCREEN_WIDTH / 2, 50, HORIZONTAL_SPACING, VERTICAL_SPACING);
                    } catch (NumberFormatException e) {
                        // Invalid age, ignore
                    }

                    inputName = "";
                    inputAgeStr = "0";
                    showInputBox = false;
                }

                // Cancel input
                if (IsKeyPressed(KEY_ESCAPE)) {
                    inputName = "";
                    inputAgeStr = "0";
                    showInputBox = false;
                }

                // Handle backspace for age first
                if (IsKeyPressed(KEY_BACKSPACE)) {
                    if (!inputAgeStr.isEmpty()) {
                        inputAgeStr = inputAgeStr.substring(0, inputAgeStr.length() - 1);
                    } else if (!inputName.isEmpty()) {
                        inputName = inputName.substring(0, inputName.length() - 1);
                    }
                }

                // Handle typed characters
                int key = GetCharPressed();
                while (key > 0) {
                    char c = (char) key;
                    if (Character.isLetter(c)) {
                        inputName += c;
                    } else if (Character.isDigit(c)) {
                        inputAgeStr += c;
                    }
                    key = GetCharPressed();
                }
            }

            // Click to select a person and add a child
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT) && !showInputBox) {
                Vector2 mousePos = GetMousePosition();
                for (Personne p : getAllPeople(racine)) {
                    if (isMouseOverPerson(p, mousePos)) {
                        selectedPerson = p;
                        inputName = "";
                        inputAgeStr = "0";
                        showInputBox = true;
                        break;
                    }
                }
            }

            BeginDrawing();
            ClearBackground(RAYWHITE);
            drawFamilyTree(racine);
            displayInputBox();

            DrawText("Arbre Généalogique", 20, 20, 30, DARKGRAY);
            DrawText("Appuyez sur ESC pour quitter", 20, SCREEN_HEIGHT - 40, 20, DARKGRAY);
            EndDrawing();
        }

        CloseWindow();
    }

    private boolean isMouseOverPerson(Personne person, Vector2 mousePos) {
        return mousePos.x() >= person.getX() && mousePos.x() <= person.getX() + person.getWidth() &&
                mousePos.y() >= person.getY() && mousePos.y() <= person.getY() + person.getHeight();
    }

    private ArrayList<Personne> getAllPeople(Personne personne) {
        ArrayList<Personne> allPeople = new ArrayList<>();
        allPeople.add(personne);
        for (Personne child : personne.getEnfants()) {
            allPeople.addAll(getAllPeople(child));
        }
        return allPeople;
    }
}
