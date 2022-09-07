package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.CommentDAO;
import com.poke.DAO.CommunityDAO;
import com.poke.domain.CommunityVO;
import com.poke.DAO.CommentDAO;

public class commentDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		int post_number = Integer.parseInt(request.getParameter("post_number"));
		CommentDAO dao = new CommentDAO();
		dao.CommentDelete(comment_num);
		
		CommunityDAO dao1 = new CommunityDAO();
		dao1.CommunityCount(post_number);
		CommunityVO vo1 = dao1.ViewCommunityOne(post_number);
		request.setAttribute("vo", vo1);
		return "CommunityRead";
	}

}
