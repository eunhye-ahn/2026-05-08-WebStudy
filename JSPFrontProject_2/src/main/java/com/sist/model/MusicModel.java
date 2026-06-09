package com.sist.model;

import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MusicDAO;
import com.sist.dao.MusicVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
 * 1. request 전송 return "../main/main.do"
 * 2. sendRedirect (request버리기)		return "redirect:list.do"
 * 3. javascript 호환 => void 
 */
@Controller
public class MusicModel {
	@RequestMapping("js/list.do")
	public void musicListData(HttpServletRequest request, HttpServletResponse response) {
		//db연결
		List<MusicVO> list = MusicDAO.musicListData();	//[]
		
		//js에 보낼 JSON만들기
		try {
			JSONArray arr = new JSONArray();
			for(MusicVO vo : list) {	//{}
				JSONObject obj = new JSONObject();
				obj.put("no",vo.getNo());
				obj.put("title",vo.getTitle());
				obj.put("poster",vo.getPoster());
				obj.put("singer",vo.getSinger());
				obj.put("album",vo.getAlbum());
				obj.put("state",vo.getState());
				obj.put("idcrement",vo.getIdcrement());
				arr.add(obj);
			}
			//JSON만 보내기위해 설정
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(arr.toJSONString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
