package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.GardenDAO;
import com.poke.domain.Garden;
import com.poke.domain.Plant_care;

public class PlantCalendarViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 아이디 데이터 가져오기
		String id = request.getParameter("id");
		// 식물 정보 가져오기
		GardenDAO dao = new GardenDAO();
		List<List> calendarList = dao.getAllPlantList(id);

		System.out.println(calendarList);

		Gson g = new Gson();
		String json = g.toJson(calendarList);

		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);

		return "NotPageMove";

	}

}