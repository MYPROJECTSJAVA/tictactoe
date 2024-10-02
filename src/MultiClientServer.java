import java.io.*;
import java.net.*;
import java.util.*;

public class MultiClientServer {
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is listening on port 5000...");

            // Continuously listen for client connections
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected!");

                // Handle client in a separate thread
                new Thread(new ClientHandler(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Inner class to handle each connected client
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Setup input and output streams for communication
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Add client writer to the shared set
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                // Read messages from this client and broadcast to all others
                String message;
                while ((message = input.readLine()) != null) {
                    System.out.println("Received: " + message);
                    broadcast(message);
                }

                // Remove client from the set when disconnected
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }

                input.close();
                out.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Method to broadcast a message to all connected clients
        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println("Broadcast: " + message);
                }
            }
        }
    }
}