package edu.gu.maze.view;


import edu.gu.maze.controller.TotalScoreController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by Matildaandersson on 15-05-27.
 */
public class TotalScoreView {

    BorderPane layput;
    Button backButton;
    //A list of highscore and playername, date .
    String[] score;

    public TotalScoreView(Stage stage, String[] score){
        stage.setTitle("Maze");
        layput = new BorderPane();

        this.score = score;



        //Create the layout
        createList();
        createBackButton();
        createTop();

        //sets the background onthe the scene
        layput.setStyle("-fx-background-image: url(\"highscore.jpg\");");

        //Sets the scene
        Scene highScoreScene = new Scene(layput, 800, 620);
        stage.setScene(highScoreScene);
    }

    //shows the list with all highscore for every player that
    //have played the game
    private void createList(){
        VBox vBox = new VBox();
        Label player;
        if(score.length == 0){

           player = new Label("The highscore is empty");
            player.setTextFill(Paint.valueOf("Red"));
            player.setFont(new Font(20));
            vBox.getChildren().addAll(player);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(20);

        }else{

            for(String x : score){

                player = new Label(x);
                player.setFont(new Font(20));
                vBox.getChildren().addAll(player);
                vBox.setAlignment(Pos.CENTER);
                vBox.setSpacing(20);

            }
        }

        layput.setCenter(vBox);

    }

    //Shows the title of the stage
    private void createTop(){

        Label title = new Label("High Score");
        title.setFont(new Font(40));
        HBox hBox = new HBox();
        hBox.getChildren().addAll(title);
        hBox.setAlignment(Pos.CENTER);
        layput.setTop(hBox);


    }

    //Sets the backbutton on the bottom of the stage
    private void createBackButton(){

        backButton = new Button("Back to start");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(backButton);
        hBox.setAlignment(Pos.CENTER);
        layput.setBottom(hBox);


    }

    //Returns the backbutton
    public Button getBackButton() {
        return backButton;
    }
}
