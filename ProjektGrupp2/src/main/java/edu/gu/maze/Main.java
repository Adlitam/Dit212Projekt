package edu.gu.maze;


import edu.gu.maze.view.MazeMainView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
    Rectangle a, b, c, d, e;
    Circle circle;
    ArrayList<Rectangle> boxes;


    //-------------Johans-------------

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Maze");
        //Circle circle = new Circle(50,100,10, Color.MAGENTA);
        layout = new GridPane();

        Label bottom = new Label();
        bottom.setMinHeight(100);
        bottom.setMinWidth(500);
        bottom.setStyle("-fx-border-color: black;");

        VBox right = new VBox();
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
        Label points = new Label("Points");
        points.setAlignment(Pos.CENTER);
        points.setMinHeight(100);
        points.setMaxWidth(100);
        points.setStyle("-fx-border-color: black;");
        Label time = new Label("Time");
        time.setAlignment(Pos.CENTER);
        time.setMinHeight(100);
        time.setMinWidth(100);
        time.setStyle("-fx-border-color: black;");
        right.getChildren().addAll(apple,key,points,time);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(layout);
        borderPane.setBottom(bottom);
        borderPane.setRight(right);







        circle = new Circle(15.0f, Color.RED);
        a = new Rectangle(50, 50, Color.GREEN);
        b = new Rectangle(50, 50, Color.GREEN);
        c = new Rectangle(50, 50, Color.GREEN);
        d = new Rectangle(50, 50, Color.GREEN);
        e = new Rectangle(50, 50, Color.GREEN);
        layout.add(a, 2, 0);
        layout.add(b, 2, 1);
        layout.add(c, 2, 2);
        layout.add(d, 2, 3);
        layout.add(e, 2, 4);
        boxes = new ArrayList();
        boxes.add(a);
        boxes.add(b);
        boxes.add(c);
        boxes.add(d);
        boxes.add(e);
        layout.add(circle, 2, 4);
        layout.getRowConstraints().add(new RowConstraints(50));
        layout.getRowConstraints().add(new RowConstraints(50));
        layout.getRowConstraints().add(new RowConstraints(50));
        layout.getRowConstraints().add(new RowConstraints(50));
        layout.getRowConstraints().add(new RowConstraints(50));
        layout.getColumnConstraints().add(new ColumnConstraints(50));
        layout.getColumnConstraints().add(new ColumnConstraints(50));
        layout.getColumnConstraints().add(new ColumnConstraints(50));
        layout.getColumnConstraints().add(new ColumnConstraints(50));
        layout.getColumnConstraints().add(new ColumnConstraints(50));
        layout.setValignment(circle, javafx.geometry.VPos.CENTER);
        layout.setHalignment(circle, javafx.geometry.HPos.CENTER);
        Scene scene = new Scene(borderPane, 500, 500, Color.BLACK);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        moveup();
                        break;
                    case DOWN:
                        movedown();
                        break;
                }
            }
        });










        primaryStage.setScene(scene);

        primaryStage.show();
    }



    //-------------Johans-------------


    private void moveup() {
        if (YPOS <= 1) return;
        for (Rectangle r : boxes) {
            layout.setRowIndex(r, layout.getRowIndex(r) + 1);
        }
        YPOS--;
    }

    private void movedown() {
        if (YPOS >= YMAX) return;
        for (Rectangle r : boxes) {
            layout.setRowIndex(r, layout.getRowIndex(r) - 1);
        }
        YPOS++;
    }
}
