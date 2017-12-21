/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.ServerSocket;  // The server uses this to bind to a port
import java.net.Socket;        // Incoming connections are represented as sockets
import java.io.*;
/**
 *
 * @author Jose
 */
public class ApplicationServer {

    /**
     */
    
    public static final int SERVER_PORT = 8765;
    public static void main(String[] args) {
        DeviceHandler dh = new DeviceHandler();
        PersonHandler ph = new PersonHandler();
        ph.DeserializePersonList();
        dh.DeserializeDeviceList();
        try{
			final ServerSocket ss = new ServerSocket(SERVER_PORT);
			Socket sock = null;
			ServerThread thread = null;
			while(true){
				sock = ss.accept();
				thread = new ServerThread(sock, ph, dh);
				thread.start();
			}
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}
