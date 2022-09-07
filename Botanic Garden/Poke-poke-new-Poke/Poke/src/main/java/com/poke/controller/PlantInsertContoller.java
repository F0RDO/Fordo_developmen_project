package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.GardenDAO;
import com.poke.domain.PlantInsert;

public class PlantInsertContoller implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 아이디 받아오기,식물 이름 , 애칭, 마지막으로 물주는 날
		String id = request.getParameter("id");
		String plant_name = request.getParameter("plant_name");
		String plant_nickname = request.getParameter("plant_nickname");
		String plant_lastdate = request.getParameter("plant_lastdate");
		
		
		PlantInsert plant = new PlantInsert();
		plant.setId(id);
		plant.setPlant_name(plant_name);
		plant.setPlant_nickname(plant_nickname);
		plant.setPlant_lastdate(plant_lastdate);
		
		
		GardenDAO dao = new GardenDAO();
		dao.plantInsert(plant);
		
	
	
	
		return "NotPageMove";
	}

}
