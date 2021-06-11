package com.web.som.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.som.dto.account.AccountDTO;

//@Controller
@RequestMapping(value = "/account")
public class AccountController {

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String getJoin() {
		return "account/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String postJoin(@ModelAttribute AccountDTO dto) {
		System.out.println(dto.toString());
//		System.out.println("ID : " + dto.getId());
//		System.out.println("NICKNAME : " + dto.getNickname());
//		System.out.println("USERNAME : " + dto.getUsername());
//		System.out.println("PASSWORD : " + dto.getPassword());
//		System.out.println("EMAIL : " + dto.getEmail());
//		System.out.println("GENDER : " + dto.getGender());
//		System.out.println("AGE : " + dto.getAge());
//		System.out.println("JOINDATE : " + dto.getJoindate());
//		System.out.println("LOGINDATE : " + dto.getLogindate());
//		System.out.println("EXPIREDATE : " + dto.getExpiredate());
		return "redirect:/";
	}
}
