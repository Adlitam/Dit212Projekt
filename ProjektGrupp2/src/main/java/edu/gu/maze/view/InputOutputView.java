package edu.gu.maze.view;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InputOutputView implements PropertyChangeListener {
    private final VBox inputAndReturnAndOutput;
    private TextArea output;
    private final Rectangle2D screenSize;
    private Button backButton;

    public InputOutputView() {
        final HBox inputAndReturn = new HBox();
        inputAndReturnAndOutput = new VBox();
        screenSize = Screen.getPrimary().getVisualBounds();
        makeBackButton();
        outputArea();
        inputAndReturn.getChildren().addAll(backButton);
        inputAndReturn.setAlignment(Pos.BOTTOM_RIGHT);
        inputAndReturnAndOutput.getChildren().addAll(output, inputAndReturn);
    }

    private final void makeBackButton(){
        backButton = new Button("Back to start");
        backButton.setMinWidth(100);
    }

    private final void outputArea(){
        output = new TextArea();
        output.setPrefSize(screenSize.getWidth(),90);
        output.setEditable(false);
        output.setWrapText(true);
        output.requestFocus();
        Platform.runLater(output::requestFocus);
    }

    public VBox getInputView(){
        return inputAndReturnAndOutput;
    }

    public Button getBackButton(){
        return backButton;
    }

    public TextArea getOutput(){
        return output;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "NO_FINAL_KEY":
                output.setText("The final door is locked");
                break;
            case "NO_KEY":
                output.setText("The door is locked");
                break;
            case "NO_APPLE":
                output.setText("GIVE ME AN APPLE ARRRRRGG!!!!! \n\n\n The monster took 5 points ");
                break;
            case "KEY":
                output.setText("You opened the door with a key!");
                break;
            case "APPLE":
                output.setText("You gave the monster an apple!");
                break;
            case "QUESTION":
                final String question = (String) evt.getOldValue();
                final String[] answers = (String[]) evt.getNewValue();
                output.setText(question + "\n" + answers[0] + "  " + answers[1] + "  " + answers[2]);
                break;
            case "CHEST":
                output.setText("You opened a chest and looted it! \nYou found: \n10 points \n1 apple \n1 key");
                break;
            case "YES":
                output.clear();
                break;
            default:
        }
    }
}