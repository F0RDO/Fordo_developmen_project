package com.poke.controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.PlantDAO;
import com.poke.controller.Controller;
import com.poke.domain.Plant;
import com.poke.domain.PlantResult;

public class PlantInfoUVController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		float uv = Float.parseFloat(request.getParameter("outputVoltage"));	
		Plant plant = new Plant();
		plant.setPlant_nickname("다롱이");
		
		plant.setUv(uv);
		
		System.out.println("UV : "+uv);


		PlantDAO dao = new PlantDAO();
		dao.plant_uvInsert(plant);
		
		Runtime runtime = Runtime.getRuntime();
		
		PlantResult result = dao.plant_compareUV(plant);
		if(result.getUvResult()==null) {
			result.setUvResult("안쌔요");
		}
		if(result.getUvResult().equals("빛이 셈")) {
			try {
				runtime.exec("explorer.exe http://192.168.137.80:5000/3");
			} catch (Exception e) {
				e.printStackTrace();
			}
			//response.sendRedirect("192.168.137.80:5000/3");
		}

		

		return "NotPageMove";
	}

}
