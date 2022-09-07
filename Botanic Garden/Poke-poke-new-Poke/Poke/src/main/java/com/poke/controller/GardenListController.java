package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.poke.DAO.GardenDAO;
import com.poke.domain.Garden;
import com.poke.domain.UserInfoVO;

public class GardenListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoVO vo = (UserInfoVO)session.getAttribute("user");
		String id = vo.getId();
		GardenDAO dao = new GardenDAO();
		List<Garden> PlantList = dao.gardenSelect(id);
		
		
		Gson g = new Gson();
		String json = g.toJson(PlantList);
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		
		
		return "NotPageMove";
	}

}
