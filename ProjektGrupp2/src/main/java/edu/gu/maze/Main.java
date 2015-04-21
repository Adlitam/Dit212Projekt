package edu.gu.maze;


import edu.gu.maze.model.Game;
import edu.gu.maze.view.MazeMainView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;

/*
  Application entry class (if using standard java and Swing)
*/
public final class Main extends Application {

    private int XPOS = 2;
    private int YPOS = 5;
    private final int XMAX = 5;
    private final int YMAX = 5;

    public static void main(String[] args) {
        launch(args);
    }

    GridPane layout;
    Rectangle [] rect = new Rectangle[5];
    Circle circle;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Maze");
        layout = new GridPane();

        VBox bottom = new VBox();

        //If you want to move the circle, click in the commentField
        TextArea output = new TextArea();
        output.setMaxHeight(80);
        output.setEditable(false);
        output.setWrapText(true);
        TextField input = new TextField();
        input.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    output.setText(input.getText());
                    input.clear();
                    break;
                case UP:
                    moveup();
                    break;
                case DOWN:
                    movedown();
                    break;
             }
        });
        input.setMinHeight(20);
        bottom.setMinHeight(100);
        bottom.setMaxWidth(Double.MAX_VALUE);
        bottom.getChildren().addAll(output,input);

        // BLÄ
        VBox right = new VBox();
        right.setStyle("-fx-border-color: black;");

        Label apple = new Label("Apple");
        apple.setAlignment(Pos.CENTER);
        apple.setMinHeight(100);
        apple.setMinWidth(100);
        apple.setStyle("-fx-border-color: black;");

        Label key = new Label("Key");
        key.setAlignment(Pos.CENTER);
        key.setMinHeight(100);
        key.setMinWidth(100);
        key.setStyle("-fx-border-color: black;");

        VBox points = new VBox();
        Game gameScore = new Game();
        int currentScore = gameScore.getcurrentHighScore();
        Label text = new Label("Score");
        Label score = new Label();
        score.setText(currentScore + "");
        points.getChildren().addAll(text,score);
        points.setAlignment(Pos.CENTER);
        points.setMinHeight(100);
        points.setMaxWidth(100);
        points.setStyle("-fx-border-color: black;");

        Label time = new Label("Time");
        time.setAlignment(Pos.CENTER);
        time.setMinHeight(100);
        time.setMaxHeight(Double.MAX_VALUE);
        time.setMinWidth(100);
        time.setStyle("-fx-border-color: black;");

        right.getChildren().addAll(apple,key,points,time);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(layout);
        borderPane.setBottom(bottom);
        borderPane.setRight(right);

        circle = new Circle(15.0f, Color.RED);
        //set all rectangels to green
        //add all rectangles to the layout
        for(int i = 0;i<rect.length;i++){
            rect[i]=new Rectangle(50, 50, Color.GREEN);
            layout.add(rect[i], 2, i);
            layout.getRowConstraints().add(new RowConstraints(50));
            layout.getColumnConstraints().add(new ColumnConstraints(50));
        }


        layout.add(circle, 2, 4);
        layout.setValignment(circle, javafx.geometry.VPos.CENTER);
        layout.setHalignment(circle, javafx.geometry.HPos.CENTER);
        Scene scene = new Scene(borderPane, 500, 500, Color.BLACK);
       /* scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case UP:

                        moveup();
                        break;
                    case DOWN:
                        movedown();
                        break;
                }

        });
       */

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    private void moveup() {
        if (YPOS <= 1) return;
        for (Rectangle r : rect) {
            layout.setRowIndex(r, layout.getRowIndex(r) + 1);
        }
        YPOS--;
    }

    private void movedown() {
        if (YPOS >= YMAX) return;
        for (Rectangle r : rect) {
            layout.setRowIndex(r, layout.getRowIndex(r) - 1);
        }
        YPOS++;
    }
}
