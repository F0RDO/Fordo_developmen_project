package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poke.DAO.UserDAO;
import com.poke.domain.UserInfoVO;

public class ChangePasswordController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		String id = "";
		String moveURL = "";
		if(session.getAttribute("checkid")!=null) {
			id = (String)session.getAttribute("checkid");
			UserDAO dao = new UserDAO();
			UserInfoVO vo = new UserInfoVO();
			vo.setId(id);
			vo.setPassword(password);
			int row = dao.ChangePassword(vo);
			if(row > 0) {
				moveURL = "Login";
			}
		}else {
			moveURL = "Login";
		}
		
		return moveURL;
	}

}
