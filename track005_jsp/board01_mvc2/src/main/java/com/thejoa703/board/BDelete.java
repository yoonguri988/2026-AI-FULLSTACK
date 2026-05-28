package com.thejoa703.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BDelete
 */
@WebServlet("/BDelete")
public class BDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		request.setAttribute("bno", bno);
		request.getRequestDispatcher("board/delete.jsp?bno="+bno).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String bpass = request.getParameter("bpass");
		
		BoardDAO dao = new BoardDAO();
		int res = dao.deleteBoard(new BoardDTO(bno, bpass));
		if (res > 0) {
			out.println("<script>");
			out.println("alert('글 삭제 성공!');");
			out.println("location.href='BList'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('실패, 잘못된 비밀번호 입력입니다.');");
			out.println("location.href='BDelete?bno=" + bno + "';");
			out.println("</script>");
		}
	}
}
