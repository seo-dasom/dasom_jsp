package com.web.som.account.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.web.som.account.dto.AccountDTO;

/**
 *	데이터베이스에 직접 연결되어 조작을 수행한다
 */
@Repository		// bean에 등록
public class AccountRepositoryImpl implements AccountRepository {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public AccountDTO select(AccountDTO dto) throws Exception {
		return sqlSession.selectOne("accountMapper.selectAccount", dto);
	}
	
	@Override
	public List<AccountDTO> selectList() throws Exception {
		return sqlSession.selectList("accountMapper.selectAll");
	}
	
	@Override
	public boolean insert(AccountDTO dto) throws Exception {
		boolean result = false;
		int rs = sqlSession.insert("accountMapper.insertAccount", dto);
		if(rs == 1) {
			result = true;
		}
		return result;
	}
	
	@Override
	public int usedNickname(String nickname) throws Exception {
		return sqlSession.selectOne("accountMapper.checkNickname", nickname);
	}

	@Override
	public int usedEmail(String email) throws Exception {
		return sqlSession.selectOne("accountMapper.checkEmail", email);
	}
	
	@Override
	public AccountDTO checkUser(AccountDTO dto) throws Exception {
		return sqlSession.selectOne("accountMapper.checkLogin", dto);
	}
	
	@Override
	public boolean update(AccountDTO dto) throws Exception {
		return true;
	}
	
	@Override
	public boolean delete(AccountDTO dto) throws Exception {
		return true;
	}

	/* JdbcTemplate을 사용 했을 때 적용한 것
		
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public AccountDTO select(AccountDTO dto) throws Exception {
		String sql = "SELECT * FROM account WHERE id = ?";
		Object[] param = {dto.getId()};
		return this.jdbcTemplate.query(sql, param, this.ext);
	}
	
	private ResultSetExtractor<AccountDTO> ext = (rs) -> {
		AccountDTO data = new AccountDTO();
		if(rs.next()) {
			data.setId(rs.getInt("id"));
			data.setUsername(rs.getString("username"));
			data.setNickname(rs.getString("nickname"));
			data.setEmail(rs.getString("email"));
			data.setPassword(rs.getString("password"));
			data.setGender(rs.getString("gender"));
			data.setAge(rs.getInt("age"));
			data.setJoindate(rs.getDate("joindate"));
			data.setLogindate(rs.getDate("logindate"));
			data.setExpiredate(rs.getDate("expiredate"));
		} else {
			data.setId(-1);
			data.setUsername("존재하지 않습니다");
			data.setNickname("존재하지 않습니다");
		}
		return data;
	};
	
	private RowMapper<AccountDTO> mapper = (rs, rowNum) -> {
		// 내부에서 리스트로 자동 생성해줌
		AccountDTO data = new AccountDTO();
		data.setId(rs.getInt("id"));
		data.setUsername(rs.getString("username"));
		data.setNickname(rs.getString("nickname"));
		data.setEmail(rs.getString("email"));
		data.setPassword(rs.getString("password"));
		data.setGender(rs.getString("gender"));
		data.setAge(rs.getInt("age"));
		data.setJoindate(rs.getDate("joindate"));
		data.setLogindate(rs.getDate("logindate"));
		data.setExpiredate(rs.getDate("expiredate"));
		
		return data;
	};

	@Override
	public List<AccountDTO> selectList() throws Exception {
		String sql = "SELECT * FROM account";
		return this.jdbcTemplate.query(sql, this.mapper);
	}
	*/

}
