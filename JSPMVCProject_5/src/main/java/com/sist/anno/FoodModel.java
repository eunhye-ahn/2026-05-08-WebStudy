package com.sist.anno;

//구분자: (index)=>if문 추가
/**
 * 어노테이션은 인덱스다 찾기쉽게 구분해주는
 * invoke는 메서드 호출함수이다 (메서드명을 몰라도 호출이 가능)
 * 
 * @클래스구분자 (Type)
 * 
 * @멤버변수 구분자 (Field)
 * 
 * @생성자 구분자 (Constructor)
 * 
 * @메서드 구분자 (Method)
 */

@Controller
public class FoodModel {
	@RequestMapping("food/list.do")
	public void food_list() {
		System.out.println("food_list() call...");
	}
	@RequestMapping("food/detail.do")
	public void food_detail() {
		System.out.println("food_detail() call...");
	}
}
