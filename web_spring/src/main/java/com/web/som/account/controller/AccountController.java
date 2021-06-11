package com.web.som.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.som.account.dto.AccountDTO;
import com.web.som.account.service.AccountService;

@Controller		// bean 객체로 등록 (사용자 요청에 대한 제어(views, model 사용에 대한..)를 담당
@RequestMapping(value = "/user")	// URL 주소 맵핑
public class AccountController {
	
	@Autowired
	AccountService account;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String userList(Model m) throws Exception {
		List<AccountDTO> datas = account.accountInfoList();
		m.addAttribute("datas", datas);
		System.out.println(datas.get(0).toString());
		return "user/list";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String userDetail(Model m, @ModelAttribute AccountDTO dto) throws Exception {
		AccountDTO data = account.accountInfoDetail(dto);
		m.addAttribute("data", data);
		System.out.println(data.toString());
		return "user/detail";
	}
}
