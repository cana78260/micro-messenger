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

public class AppServer {
    private int port;
    // private Socket socket;
    private List<Socket>alloutputs ;
    // private  String message;
    
    public AppServer(Socket socket, int port) {
        // this.socket = socket;
        this.port = port;
        alloutputs = new ArrayList<Socket>();
    
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

                Thread clientThread = new Thread(new ClientHandler(socketClient,alloutputs));
                System.out.println("clientThread: " + clientThread);
                clientThread.start(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
      

    }
 
    
    
//     private String sendMessagesToAll(String message) throws IOException {
// System.out.println("alloutputs dans methode" +alloutputs.size());
//         for (Socket client :alloutputs) {
      
//             try {

//                 if(client != socket){
       
//                 OutputStream outputStream = client.getOutputStream();
              
//                 PrintStream printStream = new PrintStream(outputStream);
             
//                 printStream.println(message);
       
//                 printStream.flush();}
              
               
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//         return message;
//     }

    // public void run() {
    //     try {
    //           message = "";

    //         System.out.println("Connexion avec le client : " + socket.getInetAddress());

    //         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      
          
        
    //         PrintStream out = new PrintStream(socket.getOutputStream());
           
       

          
    //         out.println("Bienvenue à toi  " + "\n");
         
    //         System.out.println("message avant la boucle: " + message);
                      
    //         while(!message.equals("disconnect") ){
              
            
    //           message=in.readLine();
               
               
    //             sendMessagesToAll("message to All: " + message);
    //              out.println( message + "\n");
    //             System.out.println("valeur du message dans run(): " + message);
             
                
              
    //         }
       
    //         in.close();
    //         out.close();
    //         socket.close();}
          
    //      catch (Exception e) {
    //         e.printStackTrace();
    //     }

   
    // }

}

