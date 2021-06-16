package com.web.som.board.repository;

import java.util.*;

import com.web.som.board.dto.BoardDTO;

public interface BoardRepository {
    public BoardDTO select(BoardDTO dto) throws Exception;
    public List<BoardDTO> selectAll() throws Exception;
    public List<BoardDTO> selectList(BoardDTO dto) throws Exception;
    public boolean insert(BoardDTO dto) throws Exception;
    public boolean update(BoardDTO dto) throws Exception;
    public boolean delete(BoardDTO dto) throws Exception;
}