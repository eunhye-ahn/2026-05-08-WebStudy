package com.sist.model;

import java.util.List;

import com.sist.dao.MusicDAO;
import com.sist.dao.MusicVO;

import jakarta.servlet.http.HttpServletRequest;

//데이터관리 => jsp안에서 처리 => 유지보수,확장성을 위해 => java로 변경
//model => dao,vo포함
public class MusicModel {
	public void musicListData(HttpServletRequest request) {
		//사용자 요청정보받기
		String strpage = request.getParameter("page");
		if(strpage == null){
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);

		//db연결
		MusicDAO dao = MusicDAO.newInstance();
		List<MusicVO> list = dao.musicListData(curpage);
		int totalpage = 10;

		//list.jsp에 출력할 데이터 전송 => request에 반환데이터 담기 
		//다른 jsp에서도 사용한다면 => session에 반환데이터 담기
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
	}
}
