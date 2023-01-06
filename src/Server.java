package day04workshop.src;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static void main(String[] args) throws IOException {
        
        //create a server socket and listen to a specific port
        System.out.println("starting server on port 3000");
        ServerSocket server = new ServerSocket(3000);

        double result;

        System.out.println("waiting incoming connection");
        Socket connection = server.accept();
        
        while (true) {
        //wait for a connection
            
            System.out.println("got a connection");

            //get the input stream, read the data from the client
            InputStream is = connection.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            int num1 = ois.readInt(); //reading input from client in order
            String opr = ois.readUTF();
            int num2 = ois.readInt();

            //send the result back to client
            switch (opr) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "/":
                    result = (double) num1 / (double) num2;
                    break;
                case "*":
                    result = (double) num1 * (double) num2;
                    break;
                default:
                    result = 0;
                    break;
            }

            OutputStream os = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeDouble(result);
            oos.flush();

        }
    }
}
