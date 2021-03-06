package com.web.som.account.service;

import java.util.List;

import com.web.som.account.dto.AccountDTO;

// Service : 비지니스 로직을 담당
public interface AccountService {
	public AccountDTO accountInfoDetail(AccountDTO dto) throws Exception;
	public List<AccountDTO> accountInfoList() throws Exception;
	public boolean join(AccountDTO dto) throws Exception;
	public boolean checkNickname(String nickname) throws Exception;
	public boolean checkEmail(String email) throws Exception;
	public void login(AccountDTO dto) throws Exception;
}
