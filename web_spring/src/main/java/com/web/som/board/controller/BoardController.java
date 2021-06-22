package com.web.som.board.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.web.som.board.dto.BoardDTO;
import com.web.som.board.dto.BoardSearchDTO;
import com.web.som.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	/**
	 * 빈 객체를 사용하여 BoardService 생성
	 */
	@Autowired
    private BoardService board;

	/**
	 * 게시판 메인 화면을 생성하기 위한 메서드
	 * /board 주소로 요청할 때 기능 동작
	 * /WEB-INF/views/board/main.jsp 로 포워딩 시킴
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView main(@ModelAttribute BoardSearchDTO search) throws Exception {
		// 전체 검색
		// 제목 검색
		// 작성자 검색
		// 게시판 구분 + 제목 검색
		// 게시판 구분 + 작성자 검색
		//@RequestParam, HttpServletRequest, @ModelAttribute
		
		System.out.println("Boardtype : " + search.getBoardtype());
		System.out.println("Searchtype : " + search.getSearchtype());
		System.out.println("Search : " + search.getSearch());

        ModelAndView mv = new ModelAndView();
        
        List<BoardDTO> boardlist = null;
        
        if(search.getBoardtype() == 0
           && search.getSearchtype() == null) {
        	boardlist = board.findAll();
        } else {
        	boardlist = board.findList(search);
        }
        
        mv.setViewName("board/main");
        mv.addObject("boardlist", boardlist);
        mv.addObject("boardtypes", board.getBoardTypes());
        
		return mv;
    }
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam int id) throws Exception {
		ModelAndView mv = new ModelAndView("board/detail");
		mv.addObject("item", board.findId(id));
		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView write() throws Exception {
    	ModelAndView mv = new ModelAndView("board/add");
    	mv.addObject("boardtypes", board.getBoardTypes());
        return mv;
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String write(Model m, @ModelAttribute BoardDTO dto,
    		@RequestParam MultipartFile[] file,
    		HttpServletRequest req) throws Exception {
    	String forward = "board/add";
    	String origin_name = "";
    	String change_name = "";
    	String file_ext = "";
    	ArrayList<String> permit_ext = new ArrayList<String>();
    	permit_ext.add("zip");	permit_ext.add("png");
    	
    	if(file.length <= 5) {
	    	for(MultipartFile f: file) {
	    		if(!f.isEmpty()) {
	    			if(f.getSize() <= 10 * 1024 * 1024) {
	    				// 범용 고유 식별자 128 bit
	    				UUID uuid = UUID.randomUUID();
	    				
	    				origin_name = f.getOriginalFilename();
	    				// 실제로 저장할 파일명 / 파일 다운로드 받을 때 사용해야 할 주소
	    				change_name = uuid.toString() + "_" + origin_name;
	    				file_ext = FilenameUtils.getExtension(f.getOriginalFilename());
	    				
	    				System.out.println("원본 파일명 : " + origin_name);
	    				System.out.println("변경된 파일명 : " + change_name);
		    			System.out.println("확장자 : " + file_ext);
		    			System.out.println("파일 크기(바이트) : " + f.getSize());
	    				
	    				if(permit_ext.contains(file_ext)) {
	    					System.out.println("허용되는 확장자 입니다.");
	    					
	    					// 파일 저장
	    					String root_path = req.getServletContext().getRealPath("/");
	    					File save_path = new File(root_path + "/WEB-INF/resources/file/");
	    					if(!save_path.exists()) {
	    						// 파일을 저장하기 위한 경로가 존재하지 않으면 생성
	    						Files.createDirectories(save_path.toPath());
	    					}
	    					f.transferTo(new File(save_path + "/" + origin_name));
	    				} else {
	    					System.out.println("해당 확장자는 업로드 할 수 없습니다.");
	    				}
		    			
	    			} else {
	    				System.out.println("업로드 파일의 크기가 큽니다.");
	    			}
	    		}
	    	}
    	} else {
    		System.out.println("파일 업로드 수량 초과");
    	}
    	
    	// 파일 업로드 관련 기능 완료 후 주석 해제 필요!
//    	boolean res = board.add(dto);
//    	
//    	if(res) {
//    		forward = "redirect:/board/detail?id=" + dto.getId();
//    	} else {
//    		m.addAttribute("data", dto);
//    		forward = "board/add";
//    	}
    	
        return forward;
    }

	@RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView modify(int id) throws Exception {
		ModelAndView mv = new ModelAndView("board/update");
		mv.addObject("boardtypes", board.getBoardTypes());
		mv.addObject("item", board.findId(id));
        return mv;
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String modify(Model m, @ModelAttribute BoardDTO dto) throws Exception {
        String forward = "";
        
        boolean res = board.update(dto);
        
        if(res) {
        	forward = "redirect:/board/detail?id=" + dto.getId();
        } else {
        	m.addAttribute("boardtypes", board.getBoardTypes());
        	m.addAttribute("item", dto);
        	forward = "board/update";
        }
        
		return forward;
    }

    public ModelAndView delete(int id) throws Exception {
        return null;
    }

    public ModelAndView search(String title, int btype, int aid) throws Exception {
        return null;
    }

}