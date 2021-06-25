package com.web.som.board.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
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
import com.web.som.common.FileUpload;
import com.web.som.upload.service.FileUploadService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	/**
	 * 빈 객체를 사용하여 BoardService 생성
	 */
	@Autowired
    private BoardService board;

	@Autowired
	private FileUploadService fileservice;
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
    	// 게시글 추가
    	dto.setAid(1);
    	boolean res = board.add(dto);
    	
    	if(res) {
    		//파일 업로드
    		if(file.length > 0) {
    			String root = req.getServletContext().getRealPath("/");
    			FileUpload fileupload = new FileUpload(root, "/WEB-INF/resources/file/", "/file/down/");
    			fileupload.setMaxFileCount(5);
    			fileupload.setMaxFileSize(10*1024*1024);
    			fileupload.setFileExtPermit("pdf");
    			
    			// 실제 파일 저장
    			int fcode = fileupload.save(dto.getId(), file);
    			
    			// 데이터베이스에 경로 저장
    			if(fcode > 0) {
    				for(HashMap data: fileupload.getSaveFiles()) {
    					fileservice.save(dto.getId(), data);
    					if(FilenameUtils.getExtension((String)data.get("location")).equals("png")) {
    						File f = new File(root + (String)data.get("location"));
    						BufferedImage src_img = ImageIO.read(f);
    						BufferedImage thumbnail = Scalr.resize(src_img, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 400);
    						String thumbnail_name = "thumb_" + f.getName();
    						File tf = new File(root + "/" + thumbnail_name);
    						ImageIO.write(thumbnail, "png", tf);
    					}
    				}
    			}
	    	}
    		
	    	forward = "redirect:/board/detail?id=" + dto.getId();
    	} else {
    		m.addAttribute("data", dto);
    		forward = "board/add";
    	}
    	
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