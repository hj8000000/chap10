package com.example.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	static Log log = LogFactory.getLog(LoginServlet.class);			//spring start 하면 형식에 맞게 syso보다 좀더 퀄리티 있게
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("#######");
		log.info("doGet()");
		log.info("#######");
		
		//error 처리 null이 아니면 error이기 때문에 errorMessage 셋팅  <에러처리2>
		String error = request.getParameter("error");
		log.info("error = [" + error + "]");
		if (error != null)
			request.setAttribute("errorMessage", "ID, PW를 확인하세요");  //속성에 저장해서 WEB-INF/loginform.jsp가 읽을 수 있음
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/loginform.jsp");
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("########");
		log.info("doPost()");
		log.info("########");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		log.info("id = " + id); log.info("pw = " + pw);
		
		/*
		 * 인증처리
		 */
		
		if("hj".equals(id)&&"1234".equals(pw)) {
			//인증성공
//			Cookie login = new Cookie("login", "hj");
//			login.setPath("/");
//			response.addCookie(login);
			HttpSession session = request.getSession();
			session.setAttribute("login", true);
			
			response.sendRedirect("/index.jsp");
		} else {
			//인증실패  <에러처리1>
			response.sendRedirect("/login?error");
		}
		
	}
	
}
