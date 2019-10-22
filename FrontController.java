package com.exam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.member.MemberJoinFormAction;
import com.exam.controller.member.MemberLoginFormAction;
import com.exam.controller.member.MemberjoinAction;

@WebServlet("*.do")
public class FrontController extends HttpServlet {


	//this is a servlet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		//*Front  controller functions & steps
		// 1)요청정보(명령어) 추출
		//http://localhost:80/webmvc/MemberJoinForm.do
		
		String requestURI = request.getRequestURI();
		System.out.println("요청URI 주소: " + requestURI);
		//요청 URI 주소 : /webmvc/MemberJoinForm.do
		
		String ContextPath = request.getContextPath();
		System.out.println("contextPath: " + ContextPath);
		//contextPath: /webmvc
		
		String command = requestURI.substring(ContextPath.length());
		System.out.println("command: "+ command);
		
		
		// 2)요청정보(명령어)에 대응하는 로직 실행
		Action action = null;
		ActionForward forward = null;
		ActionFactory factory = ActionFactory.getInstance();
		
		action = factory.getAction(command);
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 3)화면정보를 가진 뷰(JSP)를 선택해서 실행
		//URL 주소 (sendRedirect 방식 이동. "*.do" 명령어로 재요청할때) 
		//또는 jsp파일명(dispatch 방식 이동)
		
		if(forward !=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				final String ROOT= "./WEB-INF/views/";
				final String FORMAT = ".jsp";
				String path = ROOT +forward.getPath()+FORMAT;
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}
		}
		
		
	}//doGet method

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		//한글처리
		request.setCharacterEncoding("utf-8");
		//doGet() 메소드 호출
		doGet(request, response);
	}//doPost method

}
