package edu.gu.maze.view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartView {
    private final Button slot1Button = new Button();
    private final Button slot2Button = new Button();
    private final Button slot3Button = new Button();

    private final Button deleteSlot1 = new Button("Delete Player");
    private final Button deleteSlot2 = new Button("Delete Player");
    private final Button deleteSlot3 = new Button("Delete Player");


    private Button backButton;
    private final BorderPane layout;
    private HBox hBox1;

    protected ImageView thief;


    private String player1,player2,player3;
    private int type1,type2,type3;


    public StartView(String[] playerName, int[] playerType, Stage stage){

        stage.setTitle("Maze");
        layout = new BorderPane();
        getPlayerInfo(playerName,playerType);



        createBottom();
        createPane();
        layout.setStyle("-fx-background-image: url(\"highscore.jpg\");");
        final Scene startScene = new Scene(layout,800,600);
        stage.setScene(startScene);
    }


    private void createPane(){

        final VBox vBox = new VBox();

        for(int i = 0; i<3; i++){

            if(i == 0){
                createSlot(player1, slot1Button,deleteSlot1);
                checkImage(type1,slot1Button);
            }else if(i == 1){
                createSlot(player2, slot2Button,deleteSlot2);
                checkImage(type2,slot2Button);
            }else if(i == 2){
                createSlot(player3, slot3Button,deleteSlot3);
                checkImage(type3, slot3Button);
            }

            vBox.getChildren().addAll(hBox1);
        }

        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        layout.setCenter(vBox);
    }

    // checks the type of the player and sets the image
    private void checkImage(int type,Button slot){

        if(type == 0){
            final Image image = new Image("warrior.png");
            final ImageView warrior = new ImageView();
            warrior.setImage(image);
            warrior.setFitHeight(100);
            warrior.setFitWidth(100);
            slot.setGraphic(warrior);

        }else if(type == 1){

            final Image image = new Image("Mage.png");
            final ImageView mage = new ImageView();
            mage.setImage(image);
            mage.setFitHeight(100);
            mage.setFitWidth(100);
            slot.setGraphic(mage);

        }else if(type == 2){

            final Image image = new Image("thief.png");
            thief = new ImageView();
            thief.setImage(image);
            thief.setFitHeight(100);
            thief.setFitWidth(100);
            slot.setGraphic(thief);
        }

    }

    // Gets the info for the player
    private void getPlayerInfo(String[] playerName,int[] playerType){

        //gets the player name
        player1 = playerName[0];
        player2 = playerName[1];
        player3 = playerName[2];

        //gets the player types
        type1 = playerType[0];
        type2 = playerType[1];
        type3 = playerType[2];


    }

    //Creates one slot with a deletbutton, playername and a button with a image on if the are some player
    // that use the slot
    private void createSlot(String playerName, Button slotButton,Button delete){

        final Label player = new Label(playerName);
        player.setFont(new Font(20));
        slotButton.setMinSize(100, 100);
        hBox1 = new HBox();
        final VBox vBox = new VBox();
        vBox.getChildren().addAll(player,delete);
        vBox.setSpacing(50);
        hBox1.getChildren().addAll(slotButton,vBox);
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);

    }

    //Sets the button on the bottom of the layout
    private void createBottom(){

        final HBox hBoxBotton = new HBox();
        backButton = new Button("Back to start");
        backButton.setPrefWidth(200);
        hBoxBotton.getChildren().addAll(backButton);
        hBoxBotton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxBotton.setSpacing(10);
        layout.setBottom(hBoxBotton);
    }


    // --- Checks if some of the slots are empty
    public boolean checkSlot1(){
        return !"".equals(player1);

    }

    public boolean checkSlot2(){
        return !"".equals(player2);

    }

    public boolean checkSlot3(){
        return !"".equals(player3);

    }

    //----the get methods----
    public Button getDeleteSlot1() {
        return deleteSlot1;
    }

    public Button getDeleteSlot2() {
        return deleteSlot2;
    }

    public Button getDeleteSlot3() {
        return deleteSlot3;
    }

    public Button getSlot1Button(){
        return slot1Button;
    }

    public Button getSlot2Button(){
        return slot2Button;
    }

    public Button getSlot3Button(){
        return slot3Button;
    }

    public Button getBackButton(){
        return backButton;
    }
}