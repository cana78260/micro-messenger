package com.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class AppClient {
    public static void main(String args[]) throws Exception {
        InetAddress adrLocale = InetAddress.getLocalHost();
        System.out.println("Adresse locale = " + adrLocale.getHostAddress());
        String host = adrLocale.getHostAddress();
        int port = 19337;
        String cmd;
        String line = "";
        Socket socket = new Socket(host, port);

        BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintStream output = new PrintStream(socket.getOutputStream(), true);
        System.out.println("inputClient: " + input);
        System.out.println("outputClient: " + output);
         while( true ) {
             Scanner scan = new Scanner(System.in);
            //  input.readLine();
            //  System.out.println("input" + input.readLine());
             System.out.print(host + ":" + port + "#>");
             cmd = scan.nextLine(); // Scanning command to send to the server
             output.println("commande"+ cmd);
            line = input.readLine();
            System.out.println("line: "+ line);
         }
    }
}
