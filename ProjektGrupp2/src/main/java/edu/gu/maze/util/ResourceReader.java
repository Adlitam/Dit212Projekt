package edu.gu.maze.util;

import edu.gu.maze.model.*;
import edu.gu.maze.view.*;
import javafx.scene.image.ImageView;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public final class ResourceReader {
    //for usage, see test file
    private ResourceReader(){}
    
    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static Match readMapForModel(String filename){
        Scanner s;
        try {
        s = new Scanner(new File(filename));
        } catch (FileNotFoundException e){
            throw new RuntimeException ("Couldn't find Question file");
        }
        //The first line in the file should contain the starting x and y positions
        final int x = Integer.parseInt(s.next());
        final int y = Integer.parseInt(s.next());
        final ArrayList<ISquare[]> list = new ArrayList<>();
        while(s.hasNext()){
            ArrayList<ISquare> snd = new ArrayList<>();
            for (final char c : s.next().toCharArray()){
                if (c=='W') {
                    snd.add(new Wall());
                }   
                else if (c=='F'){
                    snd.add(new FinalDoor());
                }
                else if (c=='D'){
                    snd.add(new Door());
                }
                else if (c=='Q'){
                    snd.add(new Questioner());
                }
                else if (c=='M'){
                    snd.add(new Monster());
                }
                else if (c=='C'){
                    snd.add(new Chest());
                }
                else {
                    snd.add(new Road());
                }
            }
            list.add(snd.toArray(new ISquare[1]));
        }
        return new Match (list.toArray(new ISquare[1][1]), x, y);
    }
    
    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static Question[] readQuestions(){
        Scanner s;
        try {
            //TODO getClass.getResources edu/...
        s = new Scanner(new File("src/main/resources/edu/gu/maze/util/Questions.txt"));
        } catch (FileNotFoundException e){
            throw new RuntimeException ("Couldn't find Question file");
        }
        final ArrayList<Question> list = new ArrayList<Question>();
        while (s.hasNextLine()){
            String question;
            do {
                question = s.nextLine();
                
            } while (question.isEmpty());
            final String[] answers = new String[3];
            answers[0]= "A. " + s.nextLine();
            answers[1]= "S. " + s.nextLine();
            answers[2]= "D. " + s.nextLine();
            int right = Integer.parseInt(s.next());
            list.add(new Question(question, answers, right));
        }
        return list.toArray(new Question[1]);
    }

    @SuppressFBWarnings({"DM_DEFAULT_ENCODING", "NP_NULL_ON_SOME_PATH_EXCEPTION"})
    public static ImageView[][] readMapForView(String filename){
        Scanner s = null;
        try{
            s = new Scanner(new File(filename));
        }catch(IOException e){
            System.err.println("could not read the file: " + filename + " while trying to initialize mapView.");
        }
        s.next();
        s.next();
        final ArrayList<ImageView[]> temp1 = new ArrayList();
        int i = 0;
        while(s.hasNext()){
            int j = 0;
            ArrayList<ImageView> temp2 = new ArrayList();
            for(char c : s.next().toCharArray()){
                switch(c){
                    case 'W':
                        temp2.add(new WallView(i,j));
                        break;
                    case 'R':
                        temp2.add(new RoadView(i,j));
                        break;
                    case 'F':
                        temp2.add(new FinalDoorView(i,j));
                        break;
                    case 'D':
                        temp2.add(new DoorView(i,j));
                        break;
                    case 'M':
                        temp2.add(new MonsterView(i,j));
                        break;
                    case 'Q':
                        temp2.add(new QuestionerView(i,j));
                        break;
                    case 'C':
                        temp2.add(new ChestView(i,j));
                        break;
                    default:
                        System.err.println("Arrggghhh! Something went wrong when reading the level map for the view.");
                }
                j++;
            }
            temp1.add(temp2.toArray(new ImageView[temp2.size()]));
            i++;
        }
        return temp1.toArray(new ImageView[i][temp1.size()]);
    }

    @SuppressFBWarnings({"DM_DEFAULT_ENCODING", "NP_NULL_ON_SOME_PATH_EXCEPTION"})
    public static int getPlayerViewStartX(String filename){
        Scanner s = null;
        try{
            s = new Scanner(new File(filename));
        }catch(IOException e){
            System.err.println("could not read the file: " + filename + " while trying to initialize player in view.");
        }
        return Integer.parseInt(s.next());
    }

    @SuppressFBWarnings({"DM_DEFAULT_ENCODING", "NP_NULL_ON_SOME_PATH_EXCEPTION"})
    public static int getPlayerViewStartY(String filename){
        Scanner s = null;
        try{
            s = new Scanner(new File(filename));
        }catch(IOException e){
            System.err.println("could not read the file: " + filename + " while trying to initialize player in view.");
        }
        s.next();
        return Integer.parseInt(s.next());
    }
}
