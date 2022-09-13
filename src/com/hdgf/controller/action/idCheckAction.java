// 작성자 : 김민찬
package com.hdgf.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hdgf.dao.UsersDAO;

public class idCheckAction implements Action  {
	

//	 @RequestMapping(value="idCheckServlet")
//	스프링 어노테이션
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		System.out.println("실행확인");
		
//		request.setCharacterEncoding("UTF-8");
		// ajax로 값을 받기 때문에 UTF-8로 인코딩해준다
//		response.setCharacterEncoding("EUC-KR");
	
		String userId = request.getParameter("user_id");
		// join.jsp에서 받아온 key값이 userId이고
		// value값은 유저가 실제로 적은 값, String userId에는 value값이 들어간다.
		PrintWriter out = response.getWriter();
	
		UsersDAO user = new UsersDAO();
	
		int idCheck = user.checkId(userId);
		System.out.println(idCheck);
		// 성공여부 확인 : 개발자용
		if(idCheck==0)
		{
			System.out.println("이미 존재하는 아이디입니다.");
		}else if(idCheck==1)
		{
			System.out.println("사용 가능한 아이디입니다.");
		}
	
		out.write(idCheck+""); // --> ajax 결과값인 result가 됨
		// --> String으로 값을 내보낼 수 있도록 + "" 를 해준다
		
		
		request.setAttribute("idCheck", idCheck);
		// 이 방식이아닌 컨트롤러에서 모델로 데이터 넘기는것을 적용하자

		
	  }
		
}