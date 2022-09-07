package com.poke.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.CommunityDAO;
import com.poke.domain.CommunityVO;

public class ViewCommunityAllController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommunityDAO dao = new CommunityDAO();
		ArrayList<CommunityVO> list = dao.ViewCommunityAll();
		request.setAttribute("list", list);
		return "Community";
	}

}
