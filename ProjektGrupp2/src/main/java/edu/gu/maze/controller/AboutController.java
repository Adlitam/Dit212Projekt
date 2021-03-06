package edu.gu.maze.controller;

import edu.gu.maze.model.IGame;
import edu.gu.maze.view.AboutView;
import edu.gu.maze.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AboutController implements EventHandler<ActionEvent> {
    private final IGame model;
    private final Stage stage;
    private final AboutView view;

    public AboutController(IGame model, AboutView view, Stage primaryStage){
        this.model = model;
        this.stage = primaryStage;
        this.view = view;
        this.view.getBackButton().setOnAction(this);
    }

    //handle method for when the player presses the back to start button.
    @Override
    public void handle(ActionEvent event) {
        final Object b = event.getSource();
        if(b == view.getBackButton()){
            final MainView mainView = new MainView(stage);
            new MainController(model, mainView, stage);
        }
    }
}