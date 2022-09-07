package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.Plant_careDAO;
import com.poke.domain.Plant_care;

public class PlantCareViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		int plantcare_seq = Integer.parseInt(request.getParameter("plantcare_seq"));
		Plant_careDAO dao = new Plant_careDAO();
		Plant_care result = dao.plant_careView(plantcare_seq);

		System.out.println(result);
		
		Gson g = new Gson();
		String json = g.toJson(result);
		
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);

		
		return "NotPageMove";
	}

}
