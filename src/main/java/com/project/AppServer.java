package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AppServer implements Runnable{
    private int port;
    private Socket socket;
    private static List<Socket>alloutputs = new ArrayList<Socket>();
    private static String message;
    
    public AppServer(Socket socket, int port) {
        this.socket = socket;
        this.port = port;
    }

    public void appServerConnexion (){
 try {
            ServerSocket socketServeur = new ServerSocket(port);
            System.out.println("Lancement du serveur");
            while (true) {
                Socket socketClient = socketServeur.accept();
            
                System.out.println("nouvelle co!");
                alloutputs.add(socketClient);
                System.out.println("alloutputs"+ alloutputs);

                Thread clientThread = new Thread(new AppServer(socketClient, port));
                System.out.println("clientThread: " + clientThread);
                clientThread.start(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
      

    }
    // public static void main(String[] args) {
       
        
    // }
    // public List<Socket> post = alloutputs;
    
    private String sendMessagesToAll(String message) throws IOException {

        for (Socket client : alloutputs) {
            System.out.println("client dans la boucle: " + alloutputs);
            try {

                // if(client != socket){
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("inServer: " + in);
                // message = in.readLine();
                System.out.println("client: " + client);
                OutputStream outputStream = client.getOutputStream();
                System.out.println("outpustream dans sendMessage: " + outputStream);
                PrintStream printStream = new PrintStream(outputStream);
                System.out.println("printstream dans sendMessage: " + printStream);
                printStream.println(message);
                System.out.println("messageOut dans sendMessage: " + message);
                printStream.flush();
              
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public void run() {
        try {
              message = "";

            System.out.println("Connexion avec le client : " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("inServer: " + in);
          
        
            PrintStream out = new PrintStream(socket.getOutputStream());
           
            System.out.println("outServer: " + out);

            // message = in.readLine();
            // for(Socket client:alloutputs){
            //     PrintStream clientMessage = new PrintStream(client.getOutputStream());
            //     clientMessage.println("message dans la boucle" + message);
            //     System.out.println("message dans la boucle: " + message);
                
            // }
            // for (int i = 0; i < alloutputs.size(); i++) {
            //     out = new PrintStream(alloutputs.get(i).getOutputStream());
            //     System.out.println("outServer dans la boucle: " + out);
            //     out.println("Bonjour " + message + "\n");
            //     System.out.println("message: " + message);
            // }
            out.println("Bonjour " + "\n");
            // message = in.readLine();
            System.out.println("message avant la boucle: " + message);
                      
            while(!message.equals("disconnect") ){
              
                System.out.println("je suis dans la boucle");
              message=in.readLine();
               
                // out.println("Bonjour " + message + "\n");
                // System.out.println("message: " + message);
                sendMessagesToAll("message to All: " + message);
                 out.println( message + "\n");
                System.out.println("valeur du message dans run(): " + message);
                System.out.println("alloutputs dans run(): " + alloutputs);
                
              
            }
        // }
            in.close();
            out.close();
            socket.close();}
          
         catch (Exception e) {
            e.printStackTrace();
        }

   
    }

}

