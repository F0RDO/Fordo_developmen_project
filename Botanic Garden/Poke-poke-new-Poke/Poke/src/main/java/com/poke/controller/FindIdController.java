package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.UserDAO;
import com.poke.domain.UserInfoVO;

public class FindIdController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		UserDAO dao = new UserDAO();
		UserInfoVO vo =  (UserInfoVO)dao.FindId(email);
		String moveURL = "";
		if(vo!=null) {
			request.setAttribute("email", vo);
			moveURL = "ViewFindId";
		}else {
			moveURL = "Login";
		}
		return moveURL;
	}

}
