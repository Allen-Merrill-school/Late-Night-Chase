package Game;

//Everything needed for JavaFX
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class GameApp extends Application {
    private static GameApp instance;
    private Stage primaryStage;
    private Game game;
    private Rectangle playerRect;
    private Group gameRoot;
    private List<Obstacle> obstacles;
    private List<Rectangle> obstacleRects; //Added to keep track of obstacle rectangles
    private static boolean gameOver = false;
    private Set<KeyCode> activeKeys = new HashSet<>();

    @Override
    public void start(Stage primaryStage) {
        GameApp.instance = this;
        this.primaryStage = primaryStage;
        showMainMenu();
    }

    //Menu with start or quit
    public void showMainMenu() {
        gameOver = false;

        Label titleLabel = new Label("Rooftop Runner");
        titleLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: white;");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> startGame());

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> Platform.exit());

        VBox menuLayout = new VBox(20, titleLabel, startButton, quitButton);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setStyle("-fx-background-color: black;");

        Scene menuScene = new Scene(menuLayout, 800, 600);

        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Rooftop Runner");
        primaryStage.show();
    }

    //Start game and make everything
    public void startGame() {
        //Make player
        Player player = new Player(100, 500, 40, 40);
        game = new Game(player);

        gameRoot = new Group();
        obstacles = game.getObstacleList();
        obstacleRects = new ArrayList<>();

        //Player is rectangle 
        playerRect = new Rectangle(player.getWidth(), player.getHeight(), Color.GRAY);
        playerRect.setLayoutX(player.getX());
        playerRect.setLayoutY(player.getY());
        gameRoot.getChildren().add(playerRect);

        //SPawn platforms
        game.spawnObstacles(); 
        obstacles = game.getObstacleList(); 

        for (Obstacle obs : obstacles) {
            Rectangle obsRect = new Rectangle(obs.getWidth(), obs.getHeight(), Color.WHITE);
            obsRect.setLayoutX(obs.getX());
            obsRect.setLayoutY(obs.getY());
            gameRoot.getChildren().add(obsRect);
        }

        //Background
        Scene gameScene = new Scene(gameRoot, 800, 800, Color.BLACK);

        //Movement
        gameScene.setOnKeyPressed(e -> {
            KeyCode code = e.getCode();
            if (code == KeyCode.UP) {
                player.jump();
            }
            activeKeys.add(code);
        });

        gameScene.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
        });

        primaryStage.setScene(gameScene);
        primaryStage.show();

        //Game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameOver) {
                    stop();
                    showGameOverScreen();
                    return;
                }

                if (activeKeys.contains(KeyCode.LEFT)) {
                    player.moveLeft();
                }
                if (activeKeys.contains(KeyCode.RIGHT)) {
                    player.moveRight();
                }

                player.move();
                playerRect.setLayoutX(player.getX());
                playerRect.setLayoutY(player.getY());

                //Check if player touches ground
                if (player.getY() + player.getHeight() >= 600) {
                GameApp.gameOver();
            }

            game.updateGameState();

            }
        };
        timer.start();
    }

    public static void gameOver() {
        gameOver = true;
    }

    //Retry if fall
    public void showGameOverScreen() {
        Label gameOverLabel = new Label("Game Over!");
        gameOverLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: white;");

        Button retryButton = new Button("Retry");
        retryButton.setOnAction(e -> showMainMenu());

        VBox layout = new VBox(20, gameOverLabel, retryButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: black;");

        Scene gameOverScene = new Scene(layout, 800, 600);
        primaryStage.setScene(gameOverScene);
    }

    public static List<Obstacle> getObstacles() {
        return GameApp.instance.obstacles;
    }

    //Main method to run
    public static void main(String[] args) {
        launch(args);
    }
}
