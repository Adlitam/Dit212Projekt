package edu.gu.maze.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.gu.maze.util.Constants;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest {

    private final Game instance = new Game();

    /**
     * Test of getQuestion method, of class Game.
     */

    // Test if you get a question when using getQuestion

    @Test
    public void testGetQuestion() {
       final String question = instance.getQuestion();
        assertNotEquals(question,null);
    }

    /**
     * Test of getAnswers method, of class Game.
     */

    // Test if you get 3 possibly answers when using getAnswers();

    @Test
    public void testGetAnswers() {
        instance.getQuestion();
        final String[] result = instance.getAnswers();
        final int length = result.length;
        assertEquals(3,length);
    }


    // Checking behaviour when getQuestion() has not been called.
    @Test (expected = NullPointerException.class)
    public void testGetAnswers2(){
        instance.getAnswers();
    }

    // Checking behaviour when isThisTheRightAnswer() has been called.
    @Test (expected = NullPointerException.class)
    public void testGetAnswers3(){
        instance.startMatch(0);
        instance.getQuestion();
        instance.isThisTheRightAnswer(0);
        instance.getAnswers();
    }


    /**
     * Test of isThisTheRightAnswer method, of class Game.
     */

    // Testing calling isThisTheRightAnswer without a question
    @Test (expected = NullPointerException.class)
    public void testIsThisTheRightAnswer() {
        instance.startMatch(0);
        instance.isThisTheRightAnswer(0);
    }

    // Testing with the not available answer
    @Test (expected = IllegalArgumentException.class)
    public void testIsThisTheRightAnswer2(){
        instance.getQuestion();
        instance.getAnswers();
        instance.isThisTheRightAnswer(3);
    }

    // Test with a answer between 0 and 2
    @Test
    public void testIsThisTheRightAnswer3(){
        instance.createPlayer(0, "bla", 0);
        instance.startMatch(0);
        instance.getQuestion();
        instance.getAnswers();
        final int answer1 = instance.isThisTheRightAnswer(0);
        final boolean bool1 = answer1 == 1 | answer1==0;
        assertTrue(bool1);
        instance.getQuestion();
        instance.getAnswers();
        final int answer2 = instance.isThisTheRightAnswer(1);
        final boolean bool2 = answer2 == 1 | answer2==0;
        assertTrue(bool2);
        instance.getQuestion();
        instance.getAnswers();
        final int answer3 = instance.isThisTheRightAnswer(2);
        final boolean bool3 = answer3 == 1 | answer3 == 0;
        assertTrue(bool3);
    }


    // Calling the method twice in a row
    @Test (expected = NullPointerException.class)
    public void testIsThisTheRightAnswer4(){
        instance.startMatch(0);
        instance.getQuestion();
        instance.isThisTheRightAnswer(1);
        instance.isThisTheRightAnswer(2);
    }

    // Test if no error when using setTime()
    @Test
    public void testSetTime(){
        instance.createPlayer(0, "bla", 0);
        instance.startMatch(0);
        instance.setTime(1,4);
    }

    // Test setGamesDoneToFalse()
    @Test
    public void testSetGamesDoneToFalse(){
        instance.setGamesDoneToFalse();
        final boolean bool = instance.isTheGameDone();
        assertTrue(!bool);
    }

    // Test if you start with 0 apples
    @Test
    public void testGetApples() {
        instance.createPlayer(0, "bla", 0);
        instance.startMatch(0);
        final int apples = instance.getApples();
        assertEquals(0,apples);
    }

    // Test if you start with 0 keys
    @Test
    public void testGetKeys() {
        instance.createPlayer(0, "bla", 0);
        instance.startMatch(0);
        final int keys = instance.getKeys();
        assertEquals(0,keys);

    }

    // Test if you start with 0 points
    @Test
    public void testGetPoints() {
        instance.createPlayer(0, "bla", 0);
        instance.startMatch(0);
        final int score = instance.getPoints();
        assertEquals(0,score);
    }

    // Test IsgamesDone(), no error
    @Test
    public void testIsGamesDone(){
        instance.isTheGameDone();
    }


    @Test
    public void testNoUnexpectedErrorsinPlayerSelection(){
        //Run this in debug mode to check actual values
        instance.createPlayer(Constants.SLOT1, "Harry Potter", Constants.MAGE);
        instance.selectPlayer(Constants.SLOT1);
        instance.deletePlayer(Constants.SLOT1);
        instance.createPlayer(Constants.SLOT1, "Harry Potter", Constants.MAGE);
    }


    @Test
    public void testSerialization(){
//        Game game = new Game();
        instance.createPlayer(0, "Harry", 0);
        try
        {
            final FileOutputStream fileOut =
                    new FileOutputStream("src/main/resources/edu/gu/maze/util/gameTest.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(instance);
            out.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }
        try
        {
            final FileInputStream fileIn = new FileInputStream("src/main/resources/edu/gu/maze/util/gameTest.ser");
            final ObjectInputStream in = new ObjectInputStream(fileIn);
            in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException i)
        {
            i.printStackTrace();
        }
    }

}
