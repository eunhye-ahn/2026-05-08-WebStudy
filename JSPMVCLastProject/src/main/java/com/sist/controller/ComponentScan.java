package com.sist.controller;

import java.util.*;
import java.io.*;

public class ComponentScan {
	//패키지 단위로 모든 클래스 메모리할당
	//패키지 파일 읽기 xml/controller -> 패키지 단위 내 모든 클래스 메모리할당 com.sist.model.FoodModel
	public static List<String> componentScan(String path, String pack){
		List<String> list = new ArrayList<String>();
		try {
			path=path+File.separator+pack.replace(".", File.separator);
			//~WEB-INF/com.sist.model => ~WEB-INF/com//sist//model
			File dir = new File(path);
			
			File[] files = dir.listFiles();
			for(File f: files) {
				//System.out.println(f.getName());
				String name = f.getName();
				String ext = name.substring(name.lastIndexOf(".")+1);
				if(ext.equals("class")) {
					String clsName = name.substring(0,name.lastIndexOf("."));
					String packname = pack+"."+clsName;
					list.add(packname);
					//System.out.println(packname);
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
