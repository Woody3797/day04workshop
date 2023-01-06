package day04workshop.src;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) throws IOException {
        
        //connect to the server listening on port 3000
        //localhost or 127.0.0.1
        Socket clientconn = new Socket("127.0.0.1", 3000);

        System.out.println("connected to server on localhost:3000");

        Console cons = System.console();

        while (true) {
            String line = cons.readLine("enter equation: ");

            if (line.contains("exit")) {
                clientconn.close(); //close connection if exit is typed
                break;
            }

            OutputStream os = clientconn.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            int num1 = Integer.parseInt(line.split(" ")[0]);
            oos.writeInt(num1);
            String opr = line.split(" ")[1].trim();
            oos.writeUTF(opr);
            int num2 = Integer.parseInt(line.split(" ")[2]);
            oos.writeInt(num2);
            oos.flush();

            //receive result from server
            InputStream is = clientconn.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            double result = ois.readDouble();
            System.out.println("result: " + result);

        }

    }
}
