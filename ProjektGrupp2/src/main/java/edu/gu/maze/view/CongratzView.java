package edu.gu.maze.view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CongratzView {
    private final BorderPane layout;
    private Button backButton;
    private Button nextMap;
    private Label yourScore;

    public CongratzView(Stage stage){

        stage.setTitle("Maze");

        layout = new BorderPane();

        createBottom();
        createPane();
        layout.setStyle("-fx-background-image: url(\"highscore.jpg\");");

        final Scene startScene = new Scene(layout,800,600);
        stage.setScene(startScene);

    }

    private final void createBottom(){
        final HBox bottom = new HBox();
        nextMap = new Button("Next Map");
        nextMap.setPrefWidth(200);

        backButton = new Button("Back to start");
        backButton.setPrefWidth(200);

        bottom.getChildren().addAll(backButton,nextMap);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(20);

        layout.setBottom(bottom);




    }

    private final void createPane(){

        yourScore = new Label();
        yourScore.setFont(new Font(20));
        layout.setCenter(yourScore);



    }

    public Button getBackButton(){
        return backButton;
    }

    public Button getNextMap(){
        return nextMap;
    }

    public Label getYourScore() {
        return yourScore;
    }
}