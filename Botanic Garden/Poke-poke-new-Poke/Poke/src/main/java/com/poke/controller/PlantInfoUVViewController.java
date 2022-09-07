package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.PlantDAO;
import com.poke.domain.PlantResult;

public class PlantInfoUVViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String plant_nickname = "다롱이";
		PlantDAO dao = new PlantDAO();

		PlantResult result = dao.plant_compareUV(plant_nickname);
		Gson g = new Gson();
		String json = g.toJson(result);

		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);

		return "NotPageMove";
	}

}
