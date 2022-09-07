package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.Plant_careDAO;
import com.poke.domain.Plant_care;

public class PlantCareInsertController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 데이터 가져오기
		String plant_nickname = "다롱이";
		String last_date = null;
		String plant_diary = request.getParameter("plant_diary");
		int plant_height = Integer.parseInt(request.getParameter("plant_height"));
		
		
		Plant_care plant_care = new Plant_care();
		plant_care.setLast_date(last_date);
		plant_care.setPlant_diary(plant_diary);
		plant_care.setPlant_height(plant_height);
		plant_care.setPlant_nickname(plant_nickname);
		
		
		Plant_careDAO dao = new Plant_careDAO();
		
		dao.plant_careInsert(plant_care);
		
		
		
		
		return "Calendar";
	}

}
