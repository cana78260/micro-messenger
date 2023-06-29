package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Socket> alloutputs;
    private BufferedReader in;
    private PrintStream out;
   

    public ClientHandler(

     Socket socket,
     List<Socket> alloutputs
     )throws IOException{
            clientSocket = socket;
            this.alloutputs = alloutputs;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintStream(clientSocket.getOutputStream());
     }
    

     public void run() {
        
         try {
             String message = "";
               
             System.out.println("Connexion avec le client : " + clientSocket.getInetAddress());

            //  BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //  System.out.println("bufferedreader dans run(): " + in);

            //  PrintStream out = new PrintStream(clientSocket.getOutputStream());
            //  System.out.println("Printstream dans run(): "+ out);

             out.println("Bienvenue à toi  " + "\n");

             System.out.println("message avant la boucle: " + message);

             while (!message.equals("disconnect")) {

                 message = in.readLine();

                 sendMessagesToAll("message to All: " + message);
                 out.println(message + "\n");
                 System.out.println("valeur du message dans run(): " + message);

             }

             in.close();
             out.close();
             clientSocket.close();
         }

         catch (Exception e) {
             e.printStackTrace();
         }

     }

     private void sendMessagesToAll(String message) throws IOException {
         System.out.println("alloutputs size dans methode" + alloutputs.size());
         for (Socket client : alloutputs) {

             try {

                 if (client != clientSocket) {

                     OutputStream outputStream = client.getOutputStream();
                     System.out.println("outputstream dans sendmessagesToAll(): " + outputStream);

                     PrintStream printStream = new PrintStream(outputStream);
                    System.out.println("printstream dans sendmessagesToAll(): " + printStream);
                     printStream.println(message);

                     printStream.flush();
                 }

             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
        
     }
}
