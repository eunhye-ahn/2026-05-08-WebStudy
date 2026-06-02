package com.sist.anno;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 클래스 내 메서드롤 호출하려면 if문을 제거하고 찾기위해 구분자가 필요하다 
 * 			=> requestMapping 구분자 필요 => 어노테이션으로 제어 가능 => 유지보수가 쉽다
 * 
 * 모델 인터페이스 X => 독립적인 클래스로 생성 가능 => 결합성이 낮음 
 */

public class MainClass2 {
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);
		String[] cls = {
				"com.sist.anno.FoodModel",
				"com.sist.anno.GoodsModel",
				"com.sist.anno.MemberModel",
				"com.sist.anno.ReplyModel",
				"com.sist.anno.MainClass"
		};
		System.out.print("uri입력(goods/list.do): ");
		String uri = scan.next();
		for(String clsName : cls) {
			//클래스 메모리할당
			Class c = Class.forName(clsName);
			Object obj = c.getDeclaredConstructor().newInstance();
			Method[] method = c.getDeclaredMethods();
			for(Method m: method) {
//				System.out.println(m.getName());
				//모든 requestMapping 가져오기 
				RequestMapping rm = m.getAnnotation(RequestMapping.class);
				//내가 입력한 uri와 같은 requestMapping 의 메서드 호출
				if(rm.value().equals(uri)) {
					//invoke : 메서드호출
					System.out.println(m.invoke(obj, null));
				}
			}
		}
	}

}
