package com.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class AppClient {

    int port;


    public AppClient(){
        this.port = port;
    }

    public AppClient connexionClient(String host, int port) throws Exception{
        
       InetAddress adrLocale = InetAddress.getByName(host);
         host= adrLocale.getHostName();
        System.out.println("Adresse locale = " + adrLocale);
        
   
         port = 19337;
        String cmd;
        String line = "";
        // AppClient clientTest = new AppClient(19337);
        System.out.println("quel est ton pseudo?: ");
        java.util.Scanner inputPseudo = new Scanner(System.in);
        String pseudo = inputPseudo.next();

        Socket socket = new Socket(host, port);
        
        System.out.println("socket" + socket);
        BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintStream output = new PrintStream(socket.getOutputStream(), true);
        System.out.println("inputClient: " + input);
        System.out.println("outputClient: " + output);
        while (true) {
            Scanner scan = new Scanner(System.in);
           
            System.out.println(host+ ":" + port + "#>");
          
            while(!line.contains("disconnect")){
                cmd = scan.nextLine(); // Scanning command to send to the server
                output.println("commande de "+ pseudo +": " + cmd + "\n");
                line = input.readLine();
                // System.out.println("line: " + line);
            }
            input.close();
            output.close();
            socket.close();
        }
        
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
