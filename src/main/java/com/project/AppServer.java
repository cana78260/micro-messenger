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

public class AppServer extends Thread{
    private int port;
    private Socket socket;
    List<Socket>alloutputs = new ArrayList<Socket>();
    
    public AppServer(Socket socket, int port) {
        this.socket = socket;
        this.port = port;
    }

    public void  appServerConnexion (){
 try {
            ServerSocket socketServeur = new ServerSocket(port);
            System.out.println("Lancement du serveur");
            while (true) {
                Socket socketClient = socketServeur.accept();
                AppServer t = new AppServer(socketClient, port);
                Thread thread  = new Thread(t);
                thread.start();// a corriger
                System.out.println("nouvelle co!");
                this.alloutputs.add(socketClient);
                System.out.println("alloutputs"+ alloutputs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
// return null;

    }
    public static void main(String[] args) {
        // try {
        //     ServerSocket socketServeur = new ServerSocket(port);
        //     System.out.println("Lancement du serveur");
        //     while (true) {
        //         Socket socketClient = socketServeur.accept();
        //         AppServer t = new AppServer(socketClient);
        //         t.start();
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
            
        // }
        
    }

//   public AppServer(Socket socket, int port) {
//     this.socket = socket;
//     this.port = port;
//   }
    public void sendMessagesToAll(String message){
        for(Socket client: alloutputs){
            try{
                OutputStream outputStream = client.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                printStream.println(message);
                printStream.flush();
            }catch (IOException e){
                    e.printStackTrace();
            }
        }
    }

    public void run() {
        traitements();
    }

    public void traitements() {
        try {
            String message = "";

            System.out.println("Connexion avec le client : " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("inServer: " + in);
            // PrintStream out;
          
            // for(int i=0; i<alloutputs.size();i++){
            //      out = new PrintStream(alloutputs.get(i).getOutputStream()); 
            //      System.out.println("outServer: " + out);
            // }
        
            PrintStream out = new PrintStream(socket.getOutputStream());
           
            System.out.println("outServer: " + out);

            message = in.readLine();
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
            out.println("Bonjour " + message + "\n");
            System.out.println("message: " + message);
           
            while(!message.contains("disconnect")){
                message = in.readLine();
                // out.println("Bonjour " + message + "\n");
                // System.out.println("message: " + message);
                sendMessagesToAll("message: " + message);
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

