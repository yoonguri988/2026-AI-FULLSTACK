package com.thejoa703.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BEdit
 */
@WebServlet("/BEdit")
public class BEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));

		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.getBoard(bno);

		request.setAttribute("board", dto);
		request.getRequestDispatcher("board/edit.jsp").forward(request, response);
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

		int bno = Integer.parseInt(request.getParameter("bno"));
		String bname = request.getParameter("bname");
		String bpass = request.getParameter("bpass");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");

		BoardDAO dao = new BoardDAO();
		int rs = dao.updateBoard(new BoardDTO(bno, bname, bpass, btitle, bcontent));
		if (rs > 0) {
			out.println("<script>");
			out.println("alert('글쓰기 수정 성공!');");
			out.println("location.href='BDetail?bno=" + bno + "';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('실패, 관리자에게 문의 바랍니다!');");
			out.println("location.href='BEdit?bno=" + bno + "';");
			out.println("</script>");
		}
	}

}
