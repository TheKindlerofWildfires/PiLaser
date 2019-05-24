import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Statically on port 80 to test
 */
public class LazerServer {
    public static void main(String[] args) throws IOException {
        int portNumber = 80;
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));//Don't love input stream reader like this but w/ever
        ) {

            //So my guess is I want to input a number with directions on how to move
            //And I want out video, which is going to be interesting
            String input;
            BufferedImage output;//I know this won't be a string

            // Initiate conversation with client
            LazerProtocol lazerProtocol = new LazerProtocol();
            output = lazerProtocol.processInput(null); //Adjust this method

            out.println(output);//Want to display the image actually

            while ((input = in.readLine()) != null) { //If I'm still getting input
                output = lazerProtocol.processInput(input);
                out.println(output);//Want to display the image actually
                if (output.equals("End")) //Will want a different exit code
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }

    }

}
