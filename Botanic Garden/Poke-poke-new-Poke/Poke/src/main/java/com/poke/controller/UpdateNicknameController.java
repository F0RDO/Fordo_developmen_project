package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poke.DAO.UserDAO;
import com.poke.domain.UserInfoVO;

public class UpdateNicknameController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserInfoVO user = (UserInfoVO)session.getAttribute("user");
		String changeNickname = request.getParameter("changeNickname");
		user.setNickname(changeNickname);
		UserDAO dao = new UserDAO();
		int row = dao.UpdateNickname(user);
		if(row > 0) {
			session.setAttribute("user", user);
			System.out.println("닉네임 수정 완료");
		}
		return "redirect:/viewMyPage.do";
	}

}
