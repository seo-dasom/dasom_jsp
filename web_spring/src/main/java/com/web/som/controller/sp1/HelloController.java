package com.web.som.controller.sp1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller {
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse rsp) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sp1/hello");	// /WEB-INF/views/sp1/hello.jsp
		mv.addObject("name", "Hello");
		return mv;
	}
}
