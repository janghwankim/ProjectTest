package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//추가
import Kjh.board.*;//BoardDAO,BoardDTO
import java.sql.Timestamp;//DB에서의 필드의 날짜자료형때문에

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//1.writePro.jsp의 자바처리 구문
		request.setCharacterEncoding("utf-8");//한글처리
		
	    MateDTO article=new MateDTO();
	    
	    TendencyDTO ten=new TendencyDTO();
	    
	    article.setMate_no(Integer.parseInt(request.getParameter("mate_no")));
	    article.setId_no(Integer.parseInt(request.getParameter("id_no")));
	    article.setWriter(request.getParameter("writer"));
	    article.setGender(request.getParameter("gender"));  
	    article.setTitle(request.getParameter("title"));
	    article.setLifestyle(request.getParameter("lifestyle"));
		article.setOther_matter(request.getParameter("other_matter"));
		article.setViews(Integer.parseInt(request.getParameter("views")));
		
		
		MateDAO dbPro=new MateDAO();
		dbPro.insertArticle(article,ten);
		//3.공유->페이지 이동
		return "/writePro.jsp";//  /list.do로 처리->/list.jsp
	}
}
