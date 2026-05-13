package com.the703.basic018;

import java.io.*;
import java.net.Socket;

//3) ★InputStream-듣기 > 프로그램 > OutputStream-말하기
public class Receiver extends Thread{
	DataInputStream in;
	Socket socket;
	
	public Receiver() { super(); }
	public Receiver(Socket socket) { 
		this.socket = socket;
		try { this.in = new DataInputStream(socket.getInputStream()); }
		catch (Exception e) { e.printStackTrace(); }
	}

	@Override public void run() {
		try { 
			while(in != null) { 
				System.out.println(in.readUTF()); 
			} 
		}
		catch (Exception e) { 
			//e.printStackTrace(); 
			System.out.println("=== 통신을 마무리합니다. ===");
		}
		finally {
			try {
				if(in != null) { in.close(); }
				if(socket != null) { socket.close(); }
			}catch(Exception e) { e.printStackTrace(); }
		}
		
	}
}
