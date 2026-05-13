package com.the703.basic018;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;

//3) InputStream-듣기 > 프로그램 > OutputStream-말하기★
public class Sender extends Thread {
	DataOutputStream out;
	Socket socket;
	SimpleDateFormat sdf;
	String who;
	
	public Sender() { super(); }
	public Sender(Socket socket) { 
		this.socket = socket;
		try { this.out = new DataOutputStream(socket.getOutputStream()); }
		catch (Exception e) { e.printStackTrace(); }
		
		this.who = "["+(socket.getPort()==703?"Client":"Server");
		this.sdf = new SimpleDateFormat(" HH:mm:ss]");
	}
	
	@Override public void run() {
		//키보드로 써서 말하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try { 
			while(out != null) {
				String time = sdf.format(System.currentTimeMillis());
				// 누가, 시간
				out.writeUTF(this.who + time + br.readLine()); 
			} 
		}
		catch (Exception e) { 
			System.out.println("=== 통신을 마무리합니다. ===");
		}
		finally {
			try {
				if(out != null) out.close();
				if(br != null) br.close();
				if(!socket.isClosed()) socket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
