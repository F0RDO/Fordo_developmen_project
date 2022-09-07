package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.UserDAO;
import com.poke.domain.UserInfoVO;

public class RegisterUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		
		UserInfoVO user = new UserInfoVO(id, password, nickname, email);
		UserDAO dao = new UserDAO();
		int row = dao.RegisterUser(user);
		if(row > 0) {
			System.out.println("회원가입 성공!");
		}
		return "Login";
	}

}
