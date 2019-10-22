package com.exam.controller.member;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Action;
import com.exam.controller.ActionForward;
import com.exam.dao.MemberDao;
import com.exam.vo.MemberVO;

public class MemberjoinAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction");
		
		MemberVO memberVO=new MemberVO();
		
		//자바빈 객체에 파라미터값 찾아서 저장하기
		memberVO.setId(request.getParameter("id"));
		memberVO.setPasswd(request.getParameter("passwd"));
		memberVO.setName(request.getParameter("name"));
		memberVO.setEmail(request.getParameter("email"));
		memberVO.setAddress(request.getParameter("address"));
		memberVO.setTel(request.getParameter("tel"));
		memberVO.setMtel(request.getParameter("mtel"));
		
		//가입날짜
		memberVO.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		//DAO 객체 준비
		MemberDao memberDao = MemberDao.getInstance();
		
		//회원가입(추가) 메소드 호출
		memberDao.insertMember(memberVO);
		
		
		//로그인 페이지로 이동하는 정보 생성해서 리턴
		ActionForward forward  = new ActionForward();
		forward.setPath("memberLoginForm.do");//현재경로 기준
		forward.setRedirect(true);
		return forward;
	}

}
