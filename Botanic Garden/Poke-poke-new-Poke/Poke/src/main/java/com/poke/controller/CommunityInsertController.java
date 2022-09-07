package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poke.DAO.CommunityDAO;
import com.poke.domain.CommunityVO;
import com.poke.domain.UserInfoVO;

public class CommunityInsertController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = "";
		UserInfoVO user = new UserInfoVO();
		if(session.getAttribute("user")!=null) {
			user = (UserInfoVO)session.getAttribute("user");
			id = user.getId();
		}
		String post_name = request.getParameter("post_name");
		String post_contents = request.getParameter("post_contents");
		CommunityVO vo = new CommunityVO(0, post_name, post_contents, 0, id);
		CommunityDAO dao = new CommunityDAO();
		int row = dao.CommunityInsert(vo);
		if(row > 0) {
			System.out.println("게시글 삽입 성공!");
		}
		return "redirect:/viewCommunityAll.do";
	}

}
