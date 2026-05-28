package com.thejoa703.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BWrite
 */
@WebServlet("/BWrite")
public class BWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BWrite() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/board/write.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String bname = request.getParameter("bname");
		String bpass = request.getParameter("bpass");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bip = InetAddress.getLocalHost().getHostAddress();

		BoardDAO dao = new BoardDAO();
		int rs = dao.insertBoard(new BoardDTO(bname, bpass, btitle, bcontent, bip));
		if (rs > 0) {
			out.println("<script>");
			out.println("alert('글쓰기 성공!');");
			out.println("location.href='BList'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('글쓰기 실패, 관리자에게 문의 바랍니다.');");
			out.println("location.href='BList'");
			out.println("</script>");
		}
	}
}
