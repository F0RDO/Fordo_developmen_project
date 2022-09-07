package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.PlantDAO;
import com.poke.domain.Plant;
import com.poke.domain.PlantResult;

public class PlantInfoTemperatureController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		int humidity =  (int)Double.parseDouble(request.getParameter("Soil_moisture"));
			int temperature = (int)Double.parseDouble(request.getParameter("temperature"));
			Plant plant = new Plant();
			plant.setPlant_nickname("다롱이");

			plant.setHumidity(humidity);
			plant.setTemperature(temperature);
			System.out.println("온도 : "+temperature);
			System.out.println("토양 : "+humidity);
			PlantDAO dao = new PlantDAO();
			dao.plant_temperatureInsert(plant);
			
			PlantResult result = dao.plant_compare(plant);
			if(result.getTeperatureResult()==null) {
				result.setTeperatureResult("안쌔요");
			}
			if(result.getHumidityResult()==null) {
				result.setHumidityResult("안쌔요");
			}
			Runtime runtime = Runtime.getRuntime();
			if(result.getTeperatureResult().equals("온도 낮음")) {
				//response.sendRedirect("http://192.168.137.80:5000/2");
				try {
					runtime.exec("explorer.exe http://192.168.137.80:5000/2");
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(result.getHumidityResult().equals("물 부족")) {
				try {
					runtime.exec("explorer.exe http://192.168.137.80:5000/1");
					//Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//response.sendRedirect("http://192.168.137.80:5000/1");
			}

					
			return "NotPageMove";
	}

}
