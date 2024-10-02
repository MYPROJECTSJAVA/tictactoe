import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    static boolean turn = false;
    public static void receiveData(BufferedReader in,PrintWriter out){
        try {
            System.out.println("Server response: " + in.readLine());
        }catch(Exception e){System.out.println(e);
        }
        //wait for userInput for data to be sent to the server
        SimpleClient.sendData(in,out);
    }
    public static void sendData(BufferedReader in,PrintWriter out){
        try {
            out.println(new Scanner(System.in).nextLine());
        }catch(Exception e){System.out.println(e);}
        //wait for the server to respond
        SimpleClient.receiveData(in,out);
    }


    public static void main(String[] args){
        turn=!turn;
        try{
            Socket clientSocket = new Socket("localhost", 5000);
            System.out.println("Connected to server!!!");

            BufferedReader input=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output=new PrintWriter(clientSocket.getOutputStream(),true);


            // Step 4: Read and display the response from the server
            output.println("Hey server client here");
            System.out.println("Server response: " + input.readLine());

            // Step 5: Close connections
            input.close();
            output.close();
            clientSocket.close();


        }catch(Exception e){System.out.println(e);}

    }


}
