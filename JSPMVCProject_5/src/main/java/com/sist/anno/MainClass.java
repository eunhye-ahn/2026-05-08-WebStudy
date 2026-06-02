package com.sist.anno;

import java.util.*;
//메서드 어떻게 찾을까 
//if문 대체
/**
* 클래스 검색 => Map(if 제거)
* 메서드 => (if 제거) => 구분자 찾기 => 어노테이션
* => 리플렉션 => 메서드명과 관련없이 자동 검색 가능(메서드명 제한 없음) => Spring
* => 컨트롤러 : 이미 업체마다 제작되어있다
* 		|Spring에서 제공하는 컨트롤러
* 			
* 		|포털: 자체에서 컨트롤러 제작
* 
* MVC구조파악
* ---------Model(DAO,VO) => View(JSP,JavaScript)
* 				 --- MyBatis이해
*/

@Controller
public class MainClass {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("메뉴:맛집(1), 상품(2), 댓글(3), 회원(4): ");
		int menu = scan.nextInt();
		if(menu == 1) {	//어노테이션이 if문 대체
			System.out.print("기능:목록(1),상세보기(2): ");
			int m = scan.nextInt();
			FoodModel model = new FoodModel();
			if(m==1) {
				model.food_list();
			}
			else if(m==2) {
				model.food_detail();
			}
		}
		else if(menu == 2) {

		}
		else if(menu == 3) {

		}
		else if(menu == 4) {

		}
	}
}
