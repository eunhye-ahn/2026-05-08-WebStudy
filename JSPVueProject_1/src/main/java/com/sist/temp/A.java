package com.sist.temp;

public class A implements I{
	public void display() {
		System.out.println("A: display Call..");
	}
	//유지보수하는동안 한파트를 버리기 위해? 이러면 메인클래스에서는 컴파일에러가 안뜬다 => 근데? 결국 실행하면 런타임에러는 발생함
}
