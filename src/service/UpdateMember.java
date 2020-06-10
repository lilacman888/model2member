package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import model.MemberDTO;

public class UpdateMember implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UpdateMember");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = dao.getMember(id);
		
		
		ActionForward forward = new ActionForward();
//		forward.setRedirect(false);
//		forward.setPath("/UpdateMember.do");
		return forward;
	}

}
