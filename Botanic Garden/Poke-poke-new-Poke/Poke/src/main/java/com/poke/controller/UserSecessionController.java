package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poke.DAO.UserDAO;
import com.poke.domain.UserInfoVO;

public class UserSecessionController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoVO user = (UserInfoVO)session.getAttribute("user");
		UserDAO dao = new UserDAO();
		int row = dao.UserSecession(user);
		if(row > 0){
			System.out.println("회원탈퇴 완료!");
		}
		return "TestUserLogin";
	}
	
}
