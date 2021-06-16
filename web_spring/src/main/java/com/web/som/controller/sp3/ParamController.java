package com.web.som.controller.sp3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/param")
public class ParamController {
	
	@RequestMapping(value = "")
	public String main() {
		return "sp3/main";
	}
	
	@RequestMapping(value = "/get1", method = RequestMethod.GET)
	public ModelAndView get1(HttpServletRequest req, HttpServletResponse rsp) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sp3/get1");
		mv.addObject("value", req.getParameter("name"));
		return mv;
	}
	
	@RequestMapping(value = "/post1", method = RequestMethod.POST)
	public ModelAndView post1(HttpServletRequest req, HttpServletResponse rsp) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sp3/post1");
		mv.addObject("value", req.getParameter("name"));
		return mv;
	}
	
	@RequestMapping(value = "/get2", method = RequestMethod.GET)
	public ModelAndView get2(@ModelAttribute ParameterVO p) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sp3/get2");
		mv.addObject("obj", p);
		System.out.println(p.getName());
		System.out.println(p.getNumber());
		return mv;
	}
	
	@RequestMapping(value = "/post2", method = RequestMethod.POST)
	public String post2(Model m, @ModelAttribute ParameterVO p) {
		System.out.println(p.getName());
		System.out.println(p.getNumber());
		m.addAttribute("obj", p);
		return "sp3/post2";
	}
	
	@RequestMapping(value = "/post3", method = RequestMethod.GET)
	public String get3(Model m, @RequestParam int id) {
		System.out.println("@RequestParam int id -> " + id);
		m.addAttribute("obj", id);
		return "sp3/get3";
	}
	
}