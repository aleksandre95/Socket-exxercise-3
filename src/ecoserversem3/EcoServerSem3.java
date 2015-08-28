/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoserversem3;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class EcoServerSem3 {

    public static final int PORT = 8080;
    public static final String IP = "localhost";
    public ServerSocket serverSocket;

    private void handleClient(Socket s) {
        try {
            Scanner scanner = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            String msg = scanner.nextLine();
            
            
//            while(!msg.equals("stop")){
//                pw.println(msg.toUpperCase());
//                msg = scanner.nextLine();
    boolean check = true;
                
            while(check){
                String[] sec = msg.split("#");
            
            String part1 = sec[0];
            String part2 = sec[1];
            
            switch(part1){
                case "LOWERCASE":
                 part2 = part2.toLowerCase();
                                  
                    break;
                case "UPPERCASE":
                 part2 =part2.toUpperCase();
                 
                    break;
                case "TRANSLATE":
                    part2 = "No such translation"; 
                    
                    break;
                case "REVERSE":
                   part2 = new StringBuilder(part2).reverse().toString(); 
                   
                    break;
                case "DEFAULT":                    
                s.close();                
                    break;                   
                        
            }
            pw.println(part2);
            msg = scanner.nextLine();
            }
            check=false;
            
            System.out.println("A client was disconnected");
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(EcoServerSem3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void startServer() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(IP, PORT));
        System.out.println("Server started");
        while (true) {
            Socket socket = serverSocket.accept(); // Important : Blocking call until a connection comes in
            handleClient(socket);
            System.out.println("a New client connected");
        }

    }

    public static void main(String[] args) {
        try {
            new EcoServerSem3().startServer();
        } catch (IOException ex) {
            Logger.getLogger(EcoServerSem3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
