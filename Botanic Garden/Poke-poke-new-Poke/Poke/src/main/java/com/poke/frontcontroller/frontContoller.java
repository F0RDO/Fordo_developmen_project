package com.poke.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.controller.Controller;


@WebServlet("*.do")
public class frontContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqURL = request.getRequestURI();
		String ctx = request.getContextPath();
		String command = reqURL.substring(ctx.length());
		
		Controller controller = null;
		String view = null;
		
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		
		view = controller.execute(request, response);
		
		if (view != null) {
			if (view.indexOf("redirect:/") != -1) {
				response.sendRedirect(view.split(":/")[1]);
			}else if(view.equals("NotPageMove")){} else {
				
				RequestDispatcher rd = request.getRequestDispatcher(ViewResolver.makeView(view));
				rd.forward(request, response); 
			}
		}
	}

}
