package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;

public class Login implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Login");
		
		//보내줄 때
		response.setContentType("text/html; charset=utf-8");
		//받아줄 때
		request.setCharacterEncoding("utf-8");
		
		//세션 객체 생성
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.memberAuth(id, passwd);
		
		if(result == 1) {		// 회원인증 성공
			System.out.println("회원인증 성공");
			session.setAttribute("id", id);
		}else {					// 회원인증 실패
			System.out.println("회원인증 실패");
			out.print("<script>");
			out.print("alert('아이디 또는 비밀번호가 일치하지 않습니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/member/main.jsp");
		return forward;
	}

}
