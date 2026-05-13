package com.the703.basic018;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat001_Server {
	public static void main(String[] args) {
		//1) 서버 소켓(a/s 센터 ), 포트 바인딩(문열기)
		ServerSocket ascenter = null;
		Socket info = null;
		
		try {
			ascenter = new ServerSocket(703); // 127.0.0.1:703 [  |  |  |  ]
			System.out.println("[Server] 1. 서버준비완료 A/S 센터 OPEN.....");
		} catch (IOException e) { e.printStackTrace(); }
		
		try {
			System.out.println("[Server] 2. 고객 기다리는 중.....");
			info = ascenter.accept();
			// 연결이 들어오면 socket으로 연결
			System.out.println("[Server] 4. 고객님 연락와서 상담사랑(socket)연결함...");
			System.out.println("Hello....START =>>>");
			Thread sender = new Sender(info); sender.start();
			Thread receiver = new Receiver(info); receiver.start();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
}
/*
1. HTTP 통신    - 단방향(Client 요청이 있을때, server 응답하고 연결 종료)
2. Socket 통신  - 양방향 (특정 포트를 통해서 실시간으로 정보를 주고 받음 - tcp/udp)
3. 소켓 통신 흐름
  1) 서버 소켓(as 센터), 포트 바인딩(문열기)
  2) 클라이언트가 연결 요청, 수락
  3) 클라이언트 소켓(socket) ↔ 상담사(socket)
    (InputStream > 프로그램 > OutputStream)

*/