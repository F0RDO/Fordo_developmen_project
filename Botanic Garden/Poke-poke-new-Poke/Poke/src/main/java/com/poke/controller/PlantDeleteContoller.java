package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.GardenDAO;

public class PlantDeleteContoller implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String plant_seq = request.getParameter("plant_seq");
		System.out.println(plant_seq);
		GardenDAO dao = new GardenDAO();
		dao.GardenDelete(plant_seq);	
		Gson g = new Gson();
		String json = g.toJson("abc");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		return "NotPageMove";
	}

}
