package edu.gu.maze.controller;

import edu.gu.maze.model.Game;
import edu.gu.maze.model.IGame;
import edu.gu.maze.util.Constants;
import edu.gu.maze.view.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class MainController implements EventHandler<ActionEvent> {
    private Game model;
    private Stage stage;
    private MainView view;
    String[] playerName = new String[3];
    int[] playerType = new int[3];
    int[] playerScore = new int[3];

    public MainController(IGame model, MainView view, Stage primaryStage){
        this.stage = primaryStage;
        this.model = (Game) model;
        this.view = view;
        this.view.addController(this);
    }

    private void getPlayerInfo(){

        //gets the players score and adding it to an array
        int score1 = model.getPlayerTotalScore(Constants.SLOT1);
        int score2 = model.getPlayerTotalScore(Constants.SLOT2);
        int score3 = model.getPlayerTotalScore(Constants.SLOT3);

        playerScore[0] = score1;
        playerScore[1] = score2;
        playerScore[2] = score3;

        //Gets the players name and adding it to an array
        String player1 = model.getPlayerName(Constants.SLOT1);
        String player2 = model.getPlayerName(Constants.SLOT2);
        String player3 = model.getPlayerName(Constants.SLOT3);

        playerName[0] = player1;
        playerName[1] = player2;
        playerName[2] = player3;

        //gets the players type and adding it to an array
        int type1 = model.getPlayerType(Constants.SLOT1);
        int type2 = model.getPlayerType(Constants.SLOT2);
        int type3 = model.getPlayerType(Constants.SLOT3);

        playerType[0] = type1;
        playerType[1] = type2;
        playerType[2] = type3;


    }

    @Override
    public void handle(ActionEvent event) {
        Object b = event.getSource();
        if(b == view.getHighScoreButton()){
            getPlayerInfo();
            HighScoreView highScoreView = new HighScoreView(stage,playerName,playerScore);
            HighScoreController highScoreController = new HighScoreController(model, highScoreView, stage);
        }
        if(b == view.getPlayButton()){
            getPlayerInfo();


            StartView startView = new StartView(stage,playerName,playerType);
            StartController startController = new StartController(model, startView, stage);
        }
        if(b == view.getAboutButton()){
            AboutView aboutView = new AboutView(stage);
            AboutController aboutController = new AboutController(model, aboutView, stage);
        }
    }
}