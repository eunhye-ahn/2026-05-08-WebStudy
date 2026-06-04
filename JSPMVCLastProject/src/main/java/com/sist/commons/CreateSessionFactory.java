package com.sist.commons;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.*;
import java.io.*;

public class CreateSessionFactory {
	//파싱
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			//필요한데이터만 추출 => 파싱
			ssf = new SqlSessionFactoryBuilder().build(reader);
			//Map => (id,SQL문장)
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSsf() {
		return ssf;
	}
}
