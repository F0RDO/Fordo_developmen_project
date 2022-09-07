package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poke.DAO.CommunityDAO;
import com.poke.domain.CommunityVO;
import com.poke.domain.UserInfoVO;

public class ViewCommunityOneController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int post_number = Integer.parseInt(request.getParameter("post_number"));
		CommunityDAO dao = new CommunityDAO();
		dao.CommunityCount(post_number);
		CommunityVO vo = dao.ViewCommunityOne(post_number);
		request.setAttribute("vo", vo);
		return "CommunityRead";
	}

}
