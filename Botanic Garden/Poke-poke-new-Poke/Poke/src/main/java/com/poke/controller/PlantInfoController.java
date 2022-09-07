package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.PlantDAO;
import com.poke.domain.Plant;

public class PlantInfoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

//		int humidity = 0;
	//	int temperature = 0;
		// 삭제할 것 
		float uv = 0;
		System.out.println(request.getParameter("outputVoltage"));
		
		
		//센서 값과 식물 이름 가져오기 
		String plant_nickname = "아롱이";
//		if(request.getParameter("Soil_moisture")!=null) {
//			 humidity = Integer.parseInt(request.getParameter("Soil_moisture"));
//			System.out.println(humidity);
//			double temperature1 = Double.parseDouble(request.getParameter("temperature"));
//			temperature = (int)temperature1;
//			//int temperature = Integer.parseInt(request.getParameter("temperature"));
//			System.out.println(temperature);
//		}
		
		if(request.getParameter("outputVoltage")!= null) {
			System.out.println(request.getParameter("outputVoltage"));
			uv = Float.parseFloat(request.getParameter("outputVoltage"));
		
			System.out.println(uv);
			
		}
		
//		if (request.getParameter("uvIntensity")!= null && request.getParameter("Soil_oisture")!=null) {
//			
//			Plant plant = new Plant();
//			plant.setPlant_nickname(plant_nickname);
//			plant.setHumidity(humidity);
//			plant.setTemperature(temperature);
//			plant.setUv(uv);
//			System.out.println(plant);
//			PlantDAO dao = new PlantDAO();
//			PlantResult result = dao.plant_compare(plant);
//			System.out.println(result);
//			
//		}
		
		
		
		
		
		
		
		return "Calendar";
	}

}

