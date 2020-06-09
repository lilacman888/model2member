package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Idcheck implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Idcheck");
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		System.out.println("id : " + id);
		
		
		ActionForward forward = new ActionForward();
		
		return forward;
	}

}
