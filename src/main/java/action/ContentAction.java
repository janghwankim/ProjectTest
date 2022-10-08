package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//추가
import Kjh.board.*;

//  /content.do?num=3&pageNum=1
public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//1.content.jsp에서 처리한 자바코드를 대신실행
		//글상세보기=>(쇼핑물 상품의 정보 SangDetail.jsp?sangid=3&pageNum=1) 쇼핑물이라면
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		System.out.println("contentAction의 pageNum="+pageNum+",num=>"+num);
		
		MateDAO dbPro=new MateDAO();
		MateDTO article=dbPro.getArticle(num);
		//밑에쪽에 링크문자열의 길이를 줄이기위해서
		/*
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		System.out.println("content.jsp의 매개변수 확인용");
		System.out.println("ref="+ref+",re_step="+re_step+",re_level="+re_level);
		*/
		
		//2.실행결과 서버메모리에 저장
		request.setAttribute("num", num);//${키명}때문에 키명이랑 value값을 같게 설정함
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);// ${article.ref},, ref나 re_step level은 article속에 포함이라 article만 넘겨받으면된다
		//${ref}
		
		//3.페이지 공유
		return "/content.jsp"; //경로에 맞게 설계  
	}

}
