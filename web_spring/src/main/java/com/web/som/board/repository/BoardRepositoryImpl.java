package com.web.som.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.som.board.dto.BoardDTO;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

	@Autowired
    private SqlSession sqlSession;

	@Override
	public BoardDTO select(BoardDTO dto) throws Exception {
		return null;
	}

	@Override
	public List<BoardDTO> selectAll() throws Exception {
		return sqlSession.selectList("boardMapper.all");
	}
	
	@Override
	public List<BoardDTO> selectList(BoardDTO dto) throws Exception {
		return null;
	}

	@Override
	public boolean insert(BoardDTO dto) throws Exception {
		return false;
	}

	@Override
	public boolean update(BoardDTO dto) throws Exception {
		return false;
	}

	@Override
	public boolean delete(BoardDTO dto) throws Exception {
		return false;
	}

}