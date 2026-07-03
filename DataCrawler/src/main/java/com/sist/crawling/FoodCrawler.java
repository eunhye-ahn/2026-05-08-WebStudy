package com.sist.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.DataDAO;
import com.sist.vo.FoodVO;

/**
 * https://www.menupan.com/restaurant/bestrest/bestrest.asp?pt=rt
 * https://www.menupan.com/restaurant/bestrest/bestrest.asp?pt=wk
 * https://www.menupan.com/restaurant/bestrest/bestrest.asp?pt=nw
 * 
 * https://www.menupan.com/restaurant/bestrest/bestrest.asp?trec=8628&pt=rt&page=1
 * 
 * <p class="listName"><span class="restName"><a href="/restaurant/onepage.asp?acode=D200342" target="_blank">아름드리카페</a></span></p>
 */
public class FoodCrawler {
	private static String BASE_URL="https://www.menupan.com/restaurant/bestrest/bestrest.asp?trec=8628&pt=rt";
	private static String[] category= {
			"rt",
			"wk",
			"nw"
	};
	
	public static void main(String[] args) {
		
		DataDAO dao = DataDAO.newInstance();
		try {
			//for(int i=0;i<category.length;i++) {
				//System.out.println("번호:"+(i+1));
				for(int p=1;p<=346;p++) {
					System.out.println("===============페이지번호:"+p+"========================");
					Document doc = Jsoup.connect(BASE_URL+"&page="+p).get();
					//Elements 여러태그를 가져올 경우
					Elements link = doc.select("p.listName span.restName a");
					//System.out.println(link.toString());
					for(int j=0;j<link.size();j++) {
						try {
							System.out.println(link.get(j).attr("href"));
							String url = "https://www.menupan.com"+link.get(j).attr("href");
							Document doc2 = Jsoup.connect(url).get();
							//텍스트 자르기 : ownText()
							String name = doc2.selectFirst("div.areaBasic dl.restName dd.name").ownText().trim();
							System.out.println(name);
							Element type = doc2.selectFirst("div.areaBasic dl.restType dd.type");
							System.out.println(type.text());
							Element phone = doc2.selectFirst("div.areaBasic dl.restTel dd.tel1");
							System.out.println(phone.text());
							Element addr = doc2.selectFirst("div.areaBasic dl.restAdd dd.add1");
							System.out.println(addr.text());
							//테마 : 자동으로 ,으로 가져와짐 => 왜 테마는
							String strTheme = "";
							try {
								Element theme = doc2.selectFirst("div.areaBasic dl.restTheme dd.Theme");
								strTheme = theme.text();
								System.out.println(strTheme);
							}catch(Exception ex) {
								strTheme="없음";
							}
							Element grade = doc2.selectFirst("div.areaBasic dl.restGrade span.total");
							System.out.println(grade.text());
							
							Element price = doc2.selectFirst("div.restPrice p.price");
							System.out.println(price.text());
							
							
							// time/content/reserve/parking
							// img
							Element time = doc2.selectFirst("div.infoTable ul.tableTopA dd.txt2");
							System.out.println(time.text());
							Element content = doc2.selectFirst("div.infoTable ul.tableBottom div#info_ps_f");
							System.out.println(content.text());
							
							Element reserve = doc2.select("div.infoTable ul.tableLR dd").get(3);
							System.out.println(reserve.text());
							
							//주차 
							Elements tableLR = doc2.select("div.infoTable ul.tableLR dt");
							//System.out.println(parking.text());
							Element parking = null;
							for(int k=0;k<tableLR.size();k++) {
								String s = tableLR.get(k).text();
								if(s.equals("주차")) {
									parking = doc2.select("div.infoTable ul.tableLR dd").get(k);
								}
							}
							System.out.println(parking.text());
							
							Element poster = doc2.selectFirst("div.areaThumbnail img#rest_bigimg");
							System.out.println(poster.attr("src"));
							
							Elements image = doc2.select("div#id_restphoto_slides img[src*=/restimg/]");
							String images = "";
							for(int k=0;k<image.size();k++) {
								images+=image.get(k).attr("src")+",";
							}
							images=images.substring(0,images.lastIndexOf(","));
							System.out.println(images);
							// * ^ $
							FoodVO vo = new FoodVO();
							vo.setCno(1);
							vo.setName(name);
							vo.setType(type.text());
							vo.setPhone(phone.text());
							vo.setAddress(addr.text());
							vo.setTheme(strTheme);
							vo.setScore(Double.parseDouble(grade.text().trim()));
							vo.setPrice(price.text());
							vo.setTime(time.text());
							vo.setParking(parking.text());
							vo.setReserve(reserve.text());
							vo.setPoster(poster.attr("src"));
							vo.setImages(images);
							vo.setContent(content.text());
							
							dao.foodInsert(vo);
						}catch(Exception ex) {
							ex.printStackTrace();
						}// for end
					}//page닫기
				}//for
				System.out.println("저장완료");
			//}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
