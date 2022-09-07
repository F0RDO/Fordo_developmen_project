package com.poke.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poke.DAO.UserChallDAO;
import com.poke.domain.UserChallVO;
import com.poke.domain.UserInfoVO;

public class ViewChallUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoVO vo = (UserInfoVO)session.getAttribute("user");
		UserChallDAO dao = new UserChallDAO();
		ArrayList<UserChallVO> list = dao.ReadUserChall(vo.getId());
		request.setAttribute("UserChall", list);
		return "ChallUser";
	}

}
