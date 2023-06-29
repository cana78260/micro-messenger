package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;




public class AppClient {

    public class ServerListener implements Runnable {
        private Socket socket;
     
       
        public ServerListener(Socket socket){
            
                this.socket = socket;
        }

        public void run(){
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                  
                while((message = in.readLine()) != null){
                    System.out.println("message reçu: " + message);
                }
                in.close();
                socket.close();
            }catch (IOException error){
                    error.printStackTrace();
            }
            
        }
    }

    String hostname;
   
    int port;
   


    public AppClient(
   String hostname,
   
    int port
    ){
       
      this.hostname = hostname;
      this.port = port;
       
    }

    public void connexionClient() throws Exception{
        
       InetAddress adrLocale = InetAddress.getByName(hostname);
         hostname= adrLocale.getHostName();
        System.out.println("Adresse locale = " + adrLocale);
        
   
        String cmd;
        String line = "";
      
        System.out.println("quel est ton pseudo?: ");
        java.util.Scanner inputPseudo = new Scanner(System.in);
        String pseudo  = inputPseudo.next();

        Socket socket = new Socket(hostname, port);
        
        System.out.println("socket" + socket);
        BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintStream output = new PrintStream(socket.getOutputStream(), true);
        System.out.println("inputClient: " + input);
        System.out.println("outputClient: " + output);

        Thread displayMessages = new Thread(new ServerListener(socket));
        System.out.println("displayMessages" + displayMessages);
        displayMessages.start();
       
            Scanner scan = new Scanner(System.in);
           
            System.out.println(hostname+ ":" + port + "#>");
          
            while(true){
                LocalDateTime now = LocalDateTime.now();
                int hour = now.getHour();
                int sec = now.getSecond();
                // DateFormat dateFormat = new SimpleDateFormat("HH.ss a");
                // String ResultFormat = dateFormat(new Date());
                cmd = scan.nextLine(); // Scanning command to send to the server
                // output.println(dateFormat + pseudo + " : " + cmd + "\n");
                output.println(hour + ":" + sec + pseudo + ": " + cmd + "\n");
                // output.println("commande de "+ pseudo +": " + cmd + "\n");
              
                line = input.readLine();
                // System.out.println("line: " + line);

                if(cmd.equals("disconnect")){
                    break;
                }

            }
            input.close();
            output.close();
            socket.close();
     
        
    }


//     public static void main(String args[]) throws Exception {
//         InetAddress adrLocale = InetAddress.getLocalHost();
//         System.out.println("Adresse locale = " + adrLocale.getHostAddress());
//           System.out.println("name" + adrLocale.getByName(null));
//         String host = adrLocale.getHostAddress();
//         System.out.println("host" + host);
      
//         int port = 19337;
//         String cmd;
//         String line = "";
//         // AppClient clientTest = new AppClient(19337);
//         Socket socket = new Socket(host, port);

//         BufferedReader input = new BufferedReader(
//                 new InputStreamReader(socket.getInputStream()));
//         PrintStream output = new PrintStream(socket.getOutputStream(), true);
//         System.out.println("inputClient: " + input);
//         System.out.println("outputClient: " + output);
//          while( true ) {
//              Scanner scan = new Scanner(System.in);
//             //  input.readLine();
//             //  System.out.println("input" + input.readLine());
//              System.out.print(host + ":" + port + "#>");
//              cmd = scan.nextLine(); // Scanning command to send to the server
//              output.println("commande"+ cmd);
//             line = input.readLine();
//             System.out.println("line: "+ line);
//          }

//     }
}
