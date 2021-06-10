package com.web.som.controller.sp4;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.som.dao.account.AccountDAO;
import com.web.som.dto.account.AccountDTO;

@Controller
@RequestMapping(value = "/db")
public class DBController {
	
	@Autowired
	private AccountDAO account_dao;

	@RequestMapping(value = "")
	public String dbConn() {
		return "sp4/main";
	}
	
	@RequestMapping(value = "/first", method = RequestMethod.POST)
	public String dbConn1(@ModelAttribute AccountDTO dto) {
		int id = dto.getId();
		
		AccountDAO dao = new AccountDAO();
		AccountDTO data = dao.select(id);
		dao.close();
		
		System.out.println(data.toString());
		return "redirect:/db";
	}
	
	@RequestMapping(value = "/second", method = RequestMethod.POST)
	public String dbConn2(@ModelAttribute AccountDTO dto) {
		AccountDTO data = account_dao.selectOne(dto.getId());
		System.out.println(data.toString());
		return "redirect:/db";
	}
}
