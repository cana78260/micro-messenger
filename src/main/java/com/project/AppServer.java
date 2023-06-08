package com.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer extends Thread{
    final static int port = 19337;
    private Socket socket;

    public static void main(String[] args) {
        try {
            ServerSocket socketServeur = new ServerSocket(port);
            System.out.println("Lancement du serveur");
            while (true) {
                Socket socketClient = socketServeur.accept();
                AppServer t = new AppServer(socketClient);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }

  public AppServer(Socket socket) {
    this.socket = socket;
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
            PrintStream out = new PrintStream(socket.getOutputStream());
            System.out.println("outServer: " + out);
            message = in.readLine();
            out.println("Bonjour " + message + "\n");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
