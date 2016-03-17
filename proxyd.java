import java.io.*;
import java.net.*;

/* 
*  socket to use = 5000 + 26 = 5026
*  must hand GET and POST requests
*  no pipelining but need to have persistent connection
*  have to handle multiple parallel connections
*/

public class proxyd {


    public static void main(String[] args) throws IOException {

        proxyd proxy = new proxyd(args);
    }

    public proxyd(String[] args) throws IOException {

        int port = getPort(args);

        try {
            ServerSocket socket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IOException("an I/O error occored when creating the socket");
        }
    }

    /**
    *Takes the arguments passed into the program and returns the port number
    *@param args an array of strings that are passed into this program
    *@return the number of the port that was passed in
    */
    private static int getPort(String[] args) {
        
        int port = 0;

        if (args.length != 2) {
            throw new IllegalArgumentException("insufficient arguments");
        }

        if (!args[0].trim().equals("-port")) {
            throw new IllegalArgumentException("incorrect arguments");
        }

        try {
            port = (int)Integer.valueOf(args[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("port must be an integer");
        }

        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("port must be in the range 0-65535");
        }

        return port;
    }
}
