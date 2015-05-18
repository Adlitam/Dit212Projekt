package edu.gu.maze.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by xiang-yu on 2015-05-10.
 */
public class HighScoreView{
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    BorderPane borderPaneLayout;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }


    public HighScoreView(Stage stage){
        stage.setTitle("Maze");
        borderPaneLayout = new BorderPane();
        HBox hBoxlayout = new HBox();
        Button backButton = new Button("Back to start");
        hBoxlayout.getChildren().add(backButton);
        hBoxlayout.setAlignment(Pos.CENTER);
        backButton.setOnAction(e ->
                pcs.firePropertyChange("backButtonH", "value1", "value2"));
        borderPaneLayout.setBottom(hBoxlayout);
        create();
        Scene highScoreScene = new Scene(borderPaneLayout, 800, 600);
        stage.setScene(highScoreScene);
    }

    public void create(){
        //Add three labels
        Label highScore1 = new Label("Player 1");
        Label highScore2 = new Label("Player 2");
        Label highScore3 = new Label("Player 3");

        HBox hBoxCenter = new HBox();
        //Added the labels to the HBox
        hBoxCenter.getChildren().addAll(highScore1,highScore2,highScore3);
        hBoxCenter.setAlignment(Pos.CENTER);
        //Added the hBox to the layout.
        borderPaneLayout.setCenter(hBoxCenter);


    }

}
