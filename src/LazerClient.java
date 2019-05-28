import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Statically on port 80 to test
 */
public class LazerClient {
    public static void main(String[] args) throws IOException {
        /**
        if (args.length != 1) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }**/


        //String hostName = args[0];
        String hostName = "10.0.1.78";
        int portNumber = 80;

        try (
                Socket lpSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(lpSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(lpSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer); //This exists only for debug
                if (fromServer.equals("End"))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser); //This exists only for debug
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
