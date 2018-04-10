package com.machine.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servlet {
	final static int PORT = 8765;

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT);
			System.out.println("Server start... ");
			// 进行阻塞
			Socket socket = null;
			HandlerExecutorPool executorPool = new HandlerExecutorPool(50,1000);
			while (true) {
				 socket = server.accept(); 
				 executorPool.exeute(new ServerHandler(socket));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			server = null;
		}
	}
}
