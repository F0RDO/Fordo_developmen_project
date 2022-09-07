package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.CommentDAO;
import com.poke.DAO.CommunityDAO;
import com.poke.domain.CommentVO;
import com.poke.domain.CommunityVO;

public class CommentWriteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int post_number = Integer.parseInt(request.getParameter("post_number"));
		String id = request.getParameter("id");
		String comment_contents = request.getParameter("comment_contents");
		CommentDAO dao = new CommentDAO();
		CommentVO vo = new CommentVO(post_number,comment_contents,id);
		dao.CommentWrite(vo);
		
		CommunityDAO dao1 = new CommunityDAO();
		dao1.CommunityCount(post_number);
		CommunityVO vo1 = dao1.ViewCommunityOne(post_number);
		request.setAttribute("vo", vo1);
		return "CommunityRead";
	}

}
