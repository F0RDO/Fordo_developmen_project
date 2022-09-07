package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poke.DAO.UserDAO;
import com.poke.domain.UserInfoVO;

public class FindPasswordCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		UserInfoVO vo = new UserInfoVO();
		vo.setId(id);
		vo.setEmail(email);
		UserDAO dao = new UserDAO();
		UserInfoVO result = dao.FindPasswordCheck(vo);
		String moveURL = "";
		HttpSession session = request.getSession();
		if(result != null) {
			request.setAttribute("pwcheck", result);
			session.setAttribute("checkid", result.getId());
			moveURL = "ChangePassword";
		}else {
			moveURL = "Login";
		}
		return moveURL;
	}

}
