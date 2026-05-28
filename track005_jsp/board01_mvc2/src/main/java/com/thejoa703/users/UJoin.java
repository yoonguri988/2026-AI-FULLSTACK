package com.thejoa703.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UJoin
 */
@WebServlet("/UJoin")
public class UJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UJoin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("users/join.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String nickname = request.getParameter("nickname");
		String bpass = request.getParameter("bpass");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String bip = InetAddress.getLocalHost().getHostAddress();
		
		UserDAO dao = new UserDAO();
		int rs = dao.regUser(new UserDTO(nickname,bpass,email,mobile,bip));

		if (rs > 0) {
			out.println("<script>");
			out.println("alert('회원가입 성공!');");
			out.println("location.href='ULogin'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원가입 실패, 관리자에게 문의해주세요.');");
			out.println("location.href='UJoin'");
			out.println("</script>");
		}
	}
}
