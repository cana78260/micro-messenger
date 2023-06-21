package com.project;
public class App 


{
    public static void main( String[] args ) throws Exception
    {
      for(String arg:args ){
        System.out.println("arg:" + arg);
      }
       
        System.out.println( "Hello World!" );
        if(args.length !=2){
      AppServer testServer = new AppServer(null,Integer.parseInt(args[0]));
      System.out.println("args dans appServer" + args);
      testServer.appServerConnexion();}


      if(args.length ==2){
        AppClient test1 = new AppClient(args[0],Integer.parseInt(args[1]));
        test1.connexionClient();
        System.out.println("test1: " + test1);
      }

    }

    
}
