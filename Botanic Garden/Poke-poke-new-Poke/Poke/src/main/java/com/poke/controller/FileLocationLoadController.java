package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.RecommendDAO;
import com.poke.domain.RecommendVO;

public class FileLocationLoadController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	   	String[] recommend_num = request.getParameterValues("recommend_num");
	   	RecommendDAO dao = new RecommendDAO();
	   	ArrayList<RecommendVO> list =  dao.ViewFileLocation(recommend_num);
	   	Gson g = new Gson();
	   	String json = g.toJson(list);
	   	response.setContentType("text/json; charset=UTF-8");
	   	PrintWriter out = response.getWriter();
	   	out.println(json);
		return "NotPageMove";
	}

}
