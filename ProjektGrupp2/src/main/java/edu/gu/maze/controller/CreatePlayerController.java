package edu.gu.maze.controller;

import edu.gu.maze.model.Constants;
import edu.gu.maze.model.Game;
import edu.gu.maze.model.IGame;
import edu.gu.maze.view.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Johan on 2015-05-18.
 */
public class CreatePlayerController implements PropertyChangeListener {
    IGame model;
    Game Map;
    CreatePlayerView view;
    Stage stage;
    Constants cons = new Constants();

    private String name;
    private int type;
    private int slot;


    public CreatePlayerController(IGame model, CreatePlayerView view, Stage primaryStage){
        this.Map = (Game) model;
        this.stage = primaryStage;
        this.model = model;
        this.view = view;
        this.view.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "backButtonH":
                MainView mainView = new MainView(stage);
                new MainController(model, mainView, stage);
                break;

            case "playButton":
                InfoView infoView = new InfoView();
                new InfoController(model, infoView, stage);
                InputOutputView inputView = new InputOutputView();
                new InputOutputController(model, inputView, stage);
                MapView1 mapView1 = new MapView1();
                Map.addPropertyChangeListener(mapView1);
                new MapController(model, mapView1, inputView, stage);
                new GameView(stage, mapView1, infoView, inputView);

                TextField input = (TextField) evt.getOldValue();
                this.name = input.getText();

                model.createPlayer(1,name,type);

                break;

            case "mage":
                ImageView mage = (ImageView) evt.getOldValue();
                mage.setVisible(false);
                this.type = cons.MAGE;
                break;

            case "warrior":
                ImageView warrior = (ImageView) evt.getOldValue();
                warrior.setVisible(false);
                this.type = cons.WARRIOR;
                break;

            case "thief":
                ImageView thief = (ImageView) evt.getOldValue();
                thief.setVisible(false);
                this.type = cons.THIEF;
                break;
        }
    }
}

