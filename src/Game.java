import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Game {
    //array representation of the game
    private static String[] Board = new String[9];
    static final String[] winner = {""};
    static ImageIcon cross = new ImageIcon("C:/Users/Avishkar Shrestha/Downloads/cross.jpeg");
    static ImageIcon zero = new ImageIcon("C:/Users/Avishkar Shrestha/Downloads/zero.jpeg");
    static Player p1;
    static Player p2;
    // some varables to track player turns and update Board
    static final boolean[] turn = {false};

    //[0,1,2,3,4,5,6,7,8]
    //need to pass Board at various stages of the game to check if someone has won
    public static String logic(String[] arr) {
        //row
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        for (int i : new int[]{0, 3, 6}) {
            if (arr[i] != null && Objects.equals(arr[i], arr[i + 1]) && Objects.equals(arr[i], arr[i + 2])) {
                System.out.println("I am here1");
                return arr[i];
            }
        }
        //column
        for (int i : new int[]{0, 1, 2}) {
            if (arr[i] != null && Objects.equals(arr[i], arr[i + 3]) && Objects.equals(arr[i], arr[i + 6])) {
                System.out.println("I am here2");
                return arr[i];
            }
        }
        //diagonal
        if (arr[4] != null && Objects.equals(arr[4], arr[0]) && Objects.equals(arr[4], arr[8])) {
            System.out.println("I am here3");
            return arr[4];
        }
        if (arr[4] != null && Objects.equals(arr[4], arr[2]) && Objects.equals(arr[4], arr[6])) {
            System.out.println("I am here4");
            return arr[4];

        }
        System.out.println("The logic winner is");
        return "";
    }

    public static void checkWinner(JButton button,JFrame frame) {
        if (turn[0]) {
            button.setIcon(cross);
            Board[parseInt(button.getText())-1] = "X";

        } else {
            button.setIcon(zero);
            Board[parseInt(button.getText())-1] = "0";
        }
        turn[0] = !turn[0];
        winner[0] = Game.logic(Board);
        if (winner[0] == "0" || winner[0]=="X") {

            JOptionPane.showMessageDialog(frame, "The winner is " + (p1.letter == winner[0] ? p1.name : p2.name));
            setBoardVisible(frame,false);
        }
        boolean hasNull=false;
        for (String element : Board) {

            if (element == null) {
                hasNull=true;
                break;

                // Exit the loop once null is found
            }
        }
        if(!hasNull){
            System.out.println("Its a tie.");
            setBoardVisible(frame,false);
        }
    }

    public static void createBoard(JFrame frame) {
        //maing a jframe


        // make various button components
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");

        // array to store all buttons

        JButton[] Button = new JButton[]{button1, button2, button3, button4, button5, button6, button7, button8, button9};
        //set the buttons in an order using layout manager
        frame.setLayout(new GridLayout(3, 3));
        Arrays.stream(Button).forEach(x -> frame.add(x));
        frame.setSize(500, 500);
        frame.setLocation(100,100);

        // adding actionListeners and actionEvents to the buttons
        Arrays.stream(Button).forEach(button -> button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(button.getText());
                button.removeActionListener(this);
                Game.checkWinner(button,frame);


            }
        }));
    }

    public static void setBoardVisible(JFrame frame, boolean b) {
        frame.setVisible(b);

    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("TICTACTOE");
        createBoard(frame);
        setBoardVisible(frame, true);


        // making player objects
        Game.p1 = new Player("0");
        Game.p2 = new Player("X");
        Game.p1.authenticateUser();
        Game.p2.authenticateUser();
    }
}


