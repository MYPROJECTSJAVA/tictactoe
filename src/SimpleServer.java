import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class SimpleServer {
    public static void main(String[] args){
        try {
            //server Object
            ServerSocket serverSocket = new ServerSocket(5000);

            //client socket   &    accepting clientSocket
            Socket clientSocket=serverSocket.accept();
            System.out.println("Client Connected");

            BufferedReader input=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter output=new PrintWriter(clientSocket.getOutputStream(),true);

            String message = input.readLine();
            System.out.println("Received from client: " + message);

            output.println("Message received: " + message);

            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();




        }catch(Exception e){System.out.println(e);}
        
    }
}
