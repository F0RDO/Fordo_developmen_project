package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.CommentDAO;
import com.poke.domain.CommentVO;

public class CommentLoadController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int post_number = Integer.parseInt(request.getParameter("post_number"));
		CommentDAO dao = new CommentDAO();
		ArrayList<CommentVO> vo = dao.CommentLoad(post_number);
		Gson g = new Gson();
		String json = g.toJson(vo);
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		return "NotPageMove";
	}

}
