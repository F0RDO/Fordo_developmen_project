package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.ViewRecommendDAO;
import com.poke.domain.ViewRecommendVO;

public class ViewRecommendPlantListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int choice1_num = Integer.parseInt(request.getParameter("choice1_num"));
		int choice2_num = Integer.parseInt(request.getParameter("choice2_num"));
		
		ViewRecommendVO vo = new ViewRecommendVO(choice1_num, choice2_num);
		
		ViewRecommendDAO dao = new ViewRecommendDAO();
		ArrayList<ViewRecommendVO> list = (ArrayList)dao.ViewRecommendList(vo);
		
		Gson g = new Gson();
		String json = g.toJson(list);
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		return "NotPageMove";
	}

}
