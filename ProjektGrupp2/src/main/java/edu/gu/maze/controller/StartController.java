package edu.gu.maze.controller;

import edu.gu.maze.model.Game;
import edu.gu.maze.util.Constants;

import edu.gu.maze.model.IGame;
import edu.gu.maze.view.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;




public class StartController implements EventHandler<ActionEvent> {
    private Game model;
    private Stage stage;
    private StartView view;


    public StartController(IGame model, StartView view, Stage primaryStage){
        this.model = (Game) model;
        this.stage = primaryStage;
        this.view = view;
        this.view.addController(this);
    }

    private void play(){
        InfoView infoView = new InfoView();
        new InfoController(model, infoView);
        InputOutputView inputView = new InputOutputView();
        new InputOutputViewController(model, inputView, stage);
        MapView mapView = new MapView();
        model.addPropertyChangeListener(mapView);
        new MapController(model, mapView, stage);
        new GameView(stage, mapView, infoView, inputView);
    }

    @Override
    public void handle(ActionEvent event) {
        Object b = event.getSource();
        if(b == view.getSlot1Button()){
            if(view.checkSlot1()){

                model.startMatch(Constants.MAP1);
                play();
            }else {
                CreatePlayerView createPlayerView1 = new CreatePlayerView(stage, Constants.SLOT1);
                CreatePlayerController createPlayerController1 = new CreatePlayerController(model, createPlayerView1, stage);
            }
        }
        if(b == view.getSlot2Button()){
            if(view.checkSlot2()){

                model.startMatch(Constants.MAP1);
                play();

            }else {
                CreatePlayerView createPlayerView2 = new CreatePlayerView(stage, Constants.SLOT2);
                CreatePlayerController createPlayerController2 = new CreatePlayerController(model, createPlayerView2, stage);
            }
        }
        if(b == view.getSlot3Button()){
            if(view.checkSlot3()){

                model.startMatch(Constants.MAP1);
                play();
            }else{
                CreatePlayerView createPlayerView3 = new CreatePlayerView(stage, Constants.SLOT3);
                CreatePlayerController createPlayerController3 = new CreatePlayerController(model, createPlayerView3, stage);
            }

        }
        if(b == view.getBackButton()){
            MainView mainView = new MainView(stage);
            MainController mainController = new MainController(model, mainView, stage);
        }

        deletePlayer(event);

    }

    private void deletePlayer(ActionEvent event){

        Object b = event.getSource();

        if(b == view.getDeleteSlot1()){
            model.deletePlayer(Constants.SLOT1);
            
        }
        if(b == view.getDeleteSlot2()){
            model.deletePlayer(Constants.SLOT2);
        }
        if(b == view.getDeleteSlot3()){
            model.deletePlayer(Constants.SLOT3);
        }


    }

}
