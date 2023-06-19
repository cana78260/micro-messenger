package com.project;
public class App 


{
    public static void main( String[] args ) throws Exception
    {
      for(String arg:args ){
        System.out.println("arg:" + arg);
      }
        System.out.println( "Hello World!" );
      AppServer testServer = new AppServer(null);
      testServer.appServerConnexion();

        AppClient test1 = new AppClient();
        test1.connexionClient("localhost", 19337);
        System.out.println("test1: " + test1);

    }

    
}
