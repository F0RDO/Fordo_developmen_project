package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poke.DAO.UserDAO;
import com.poke.domain.UserInfoVO;

public class UpdatePasswordController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserInfoVO user = (UserInfoVO)session.getAttribute("user");
		String changePassword = request.getParameter("changePassword");
		user.setPassword(changePassword);
		UserDAO dao = new UserDAO();
		int row = dao.UpdatePassowrd(user);
		if(row > 0) {
			session.setAttribute("user", user);
			System.out.println("패스워드 수정 완료");
		}
		return "redirect:/viewMyPage.do";
	}

}
