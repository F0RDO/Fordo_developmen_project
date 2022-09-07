package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.CommunityDAO;
import com.poke.domain.CommunityVO;

public class CommunityUpdateController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int post_number = Integer.parseInt(request.getParameter("post_number"));
		String contents = request.getParameter("changeContents");
		
		CommunityVO vo = new CommunityVO(post_number, contents);
		CommunityDAO dao = new CommunityDAO();
		int row = dao.CommunityUpdate(vo);
		if(row > 0) {
			System.out.println("업데이트 완료!");
		}
		return "redirect:/viewCommunityAll.do";
	}

}
