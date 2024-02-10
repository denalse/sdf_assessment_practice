package nus.iss.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClientApp {
    public static void main(String[] args) {

        String requestID;
        String name = "dinosaur";
        String email = "dinosaur@gmail.com";
        float avg = 0;
        int sum = 0;


        String[] connectionInfo = args[0].split(":");
        System.out.println("Entering " + connectionInfo[0] + ":" + connectionInfo[1] + "..");

        try {
            while (true) {

                Socket socket = new Socket(connectionInfo[0], Integer.parseInt(connectionInfo[1]));
                // Socket socket = server.accept();

                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);

                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);

                System.out.println("hello");
                String response = (String) ois.readObject();
                String[] line = response.split(" ");

                requestID = line[0];
                String[] nums = line[1].split(",");

                // able to print
                // System.out.println(requestID);
                // System.out.println(Arrays.toString(nums));
                
                // calculate sum
                for (String num : nums) {
                    sum += Integer.valueOf(num);
                }
                
                // calculate avg
                avg = (float) sum/nums.length;
                System.out.println(avg);
                
                oos.writeObject(requestID);
                oos.writeObject(name);
                oos.writeObject(email);
                oos.writeObject(avg);

                Boolean status = (Boolean) ois.readObject();
                if (status) {
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("FAILED");
                }
                
                // FLush the stream to ensure data is written out immediately
                oos.flush();
                oos.close();
                socket.close();

            }
        } catch (NumberFormatException | IOException e ) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
