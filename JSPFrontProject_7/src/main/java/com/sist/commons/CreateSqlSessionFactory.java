package com.sist.commons;

import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lombok.Getter;

//모든 DAO에서 사용하는 공통 소스 => 공통모듈 : AOP
public class CreateSqlSessionFactory {
	@Getter
	private static SqlSessionFactory ssf;
	static {
		try {
			//XML파싱
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
