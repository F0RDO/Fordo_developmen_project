package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.CommunityDAO;

public class CommunityDeleteController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int post_number = Integer.parseInt(request.getParameter("post_number"));
		CommunityDAO dao = new CommunityDAO();
		int row = dao.CommunityDelete(post_number);
		if(row > 0) {
			System.out.println("게시물 삭제 완료!");
		}
		return "redirect:/viewCommunityAll.do";
	}

}
