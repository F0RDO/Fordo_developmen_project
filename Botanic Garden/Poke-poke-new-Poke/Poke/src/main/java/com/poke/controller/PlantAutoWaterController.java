package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.GardenDAO;
import com.poke.DAO.PlantDAO;
import com.poke.domain.AutoWater;
import com.poke.domain.Plant;

public class PlantAutoWaterController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		
	//	String autoWater = request.getParameter("AutoWaterSelect");
		String autoWater = "true";
		
		
		System.out.println(autoWater);
		String id = "admin";
		String result = "";
		AutoWater vo = new AutoWater(id, autoWater);
		GardenDAO dao = new GardenDAO();
		

		if (autoWater.equals("true")) {
			result = "{\"MOTOR\":\"1\"}";}
		else {
			result = "{\"MOTOR\":\"0\"}";
			}
		
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
			out.print(result);
		
			
		//("{\"MOTOR\":\"1\"\"SECONDS\":\"3\"}"
		return "NotPageMove";
		
	}

}

