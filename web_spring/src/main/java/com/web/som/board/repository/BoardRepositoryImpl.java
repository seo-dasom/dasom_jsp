package com.web.som.board.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.som.board.dto.BoardDTO;
import com.web.som.board.dto.BoardSearchDTO;
import com.web.som.board.dto.BoardTypeDTO;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

	@Autowired
    private SqlSession sqlSession;

	@Override
	public BoardDTO select(BoardDTO dto) throws Exception {
		BoardDTO data = sqlSession.selectOne("boardMapper.row", dto);
		return data;
	}

	@Override
	public List<BoardDTO> selectAll() throws Exception {
		List<BoardDTO> data = sqlSession.selectList("boardMapper.all");
		return data;
	}
	
	@Override
	public List<BoardDTO> selectList(BoardDTO dto) throws Exception {
		return null;
	}

	@Override
	public boolean insert(BoardDTO dto) throws Exception {
		boolean result = false;
		int rs = 0;
		int seq = sqlSession.selectOne("boardMapper.seq");
		if(seq > 0) {
			dto.setId(seq);
			rs = sqlSession.insert("boardMapper.boardInsert", dto);
			if(rs == 1) {
				rs = sqlSession.update("boardMapper.boardCLOB", dto);
				if(rs == 1) {
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public boolean update(BoardDTO dto) throws Exception {
		boolean result = false;
		int rs = sqlSession.update("boardMapper.boardUpdate", dto);
		
		if(rs == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(BoardDTO dto) throws Exception {
		return false;
	}

	@Override
	public List<BoardTypeDTO> selectBoardTypes() throws Exception {
		List<BoardTypeDTO> data = sqlSession.selectList("boardMapper.boardtypes");
		return data;
	}

	@Override
	public List<BoardDTO> selectList(BoardSearchDTO search) throws Exception {
		List<BoardDTO> data = sqlSession.selectList("boardMapper.boardSearch", search);
		return data;
	}
	
	@Override
	public List<Map<String, String>> uploadfiles(int id) throws Exception {
		return sqlSession.selectList("boardMapper.uploadfiles", id);
	}

}