import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Player {
    String letter;
    String name;
    Connection connectDB;
    Player(String L) {

        letter = L;
        connectDB= Database.connectDatabase();
    }
    // authentication method
    void authenticateUser(){
        // just take names for now
        name=JOptionPane.showInputDialog(null,"Enter your name","");

    }
    //add new user to the database
    private void addNewUser(){
        String sql="insert into users (name,password) values ('Erica','stanley')";
        try {
            //set statement
            Statement statement = connectDB.createStatement();
            int affectedRows= statement.executeUpdate(sql);
        }catch(Exception e){System.out.println(e);}

    }

    public static void main(String[] args){
        Player p1=new Player("O");
        p1.addNewUser();
    }
}
