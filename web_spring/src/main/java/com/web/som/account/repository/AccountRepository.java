package com.web.som.account.repository;

import java.util.List;

import com.web.som.account.dto.AccountDTO;

/**
 * 데이터베이스 조작과 관련된 기능을 나열한 인터페이스
 * (데이터베이스에 연결하여 데이터를 조작하는 기능 담당)
 */
public interface AccountRepository {
	public AccountDTO select(AccountDTO dto) throws Exception;
	public List<AccountDTO> selectList() throws Exception;
	public int usedNickname(String nickname) throws Exception;
	public int usedEmail(String email) throws Exception;
	public AccountDTO checkUser(AccountDTO dto) throws Exception;
	public boolean insert(AccountDTO dto) throws Exception;
	public boolean update(AccountDTO dto) throws Exception;
	public boolean delete(AccountDTO dto) throws Exception;
}
