import java.io.*;
import java.net.*;

public class Server {

    public static String processData(String input) {
        String output;
        output = input.toUpperCase(); // PLACEHOLDER STRING PROCESS
        return output;
    }

    public static void main(String[] args) {
        int port = 5996; // Port number for the server to listen on
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port: " + port);
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                
                InputStream input = socket.getInputStream(); // Create input stream
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                
                OutputStream output = socket.getOutputStream(); // Create output stream
                PrintWriter writer = new PrintWriter(output, true);
                
                String clientInput = reader.readLine(); // Read string from client
                System.out.println("Received: " + clientInput);
                
                String processedText = processData(clientInput); // Process client's data
                
                writer.println("Processed: " + processedText); // Send a string back to client
                
                socket.close(); // Close socket once done
                System.out.println("Client disconnected");
            }
            
        } catch (IOException exception) { // If no client connects, or other server related error
            System.out.println("Server exception: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
