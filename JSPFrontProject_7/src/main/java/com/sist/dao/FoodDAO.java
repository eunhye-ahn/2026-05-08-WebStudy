package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.FoodVO;
/**
 * 	food/list.do
 * 		|	=> 찾기 @WebServlet("*.do")
 * 	DispatcherServlet(Spring의 Controller명)	=> 모든 요청 받아서 모델 찾기 
 * 		|					
 *    Model => 메소드찾기 @RequestMapping("food/list.do")
 *    	|		처리 => request.setAttribute()
 *    DispatcherServlet => request 전송 => 출력할 JSP 찾아서 전송
 *    										--------------
 *    										| => request전송
 *    												return "../food/list.jsp"
 *    										| => request초기화 =>화면이동
 *    												return "redirect:list.do"
 *    										| => Ajax를 이용한방식
 *    												void => JSON
 *    
 *    => 단점 : 모델클래스 전체를 찾는다 : 싱글턴 패턴 / Factory 패턴
 *    => 자동클래스메모리할당 : @Autowired
 *    => @ResponseBody => getParameter()없이 전송한 모든 데이터를 받을 수 있다
 */
public class FoodDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	/**
	 * <select id="foodListData" parameterType="int" resultType="FoodVO">
			SELECT no,name,poster,address
			FROM food
			ORDER BY no ASC
			OFFSET #{start} ROWS FETCH NEXT 12 ROWS NOLY
		</select>
		<select id="foodTotalPage" resultType="int">
			SELECT CEIL(COUNT(*)/12.0) FROM food
		</select>
		<select id="foodDetailData" parameterType="int" resultType="FoodVO">
			SELECT *
			FROM food
			WHERE no=#{no}
		</select>
	 */
	public static List<FoodVO> foodListData(int start){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData",start);
		session.close();
		return list;
	}
	public static int foodTotalPage(){
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodTotalPage");
		session.close();
		return total;
	}
	public static FoodVO foodDetailData(int no) {
		SqlSession session = ssf.openSession();
		FoodVO vo = session.selectOne("foodDetailData",no);
		session.close();
		return vo;
	}
	/**
	 * <select id="foodFindData" resultType="FoodVO" parameterType="hashmap">
			SELECT no,name,poster,address
			FROM food
			WHERE ${column} LIKE '%'||#{fd}||'%'
			ORDER BY no ASC
			OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		</select>
		<select id="foodFindTotalPage" resultType="int" parameterType="hashmap">
			SELECT CEIL(COUNT(*)/12.0) FROM food
			WHERE ${column} LIKE '%'||#{fd}||'%'
		</select>
	 */
	public static List<FoodVO> foodFindData(Map map){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodFindData",map);
		session.close();
		return list;
	}
	public static int foodFindTotalPage(Map map){
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodFindTotalPage",map);
		session.close();
		return total;
	}
}
