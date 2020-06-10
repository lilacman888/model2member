package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;

public class Idcheck implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Idcheck");
		
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();			//출력스트림을 만들어주는 객체
		
		String id = request.getParameter("id");
		System.out.println("id : " + id);
		
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.idcheck(id);
		System.out.println("result : " + result);		// 1 : 중복 아이디
														// -1 : 사용가능한 아이디
		out.print(result);				// 브라우저에 출력되는 값이 callback함수로 리턴됨
		return null;
	}

}
