package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import Kjh.board.*;//BoardDAO,BoardDTO
import java.util.*;

//1.requestPro()을 공통메서드로 사용  2.객체형변환떄문에(상속기법)
public class ListAction2 implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//1. /list.jsp에서 처리했던 자바코드를 대신처리해주고 그 결과->전달
		
		int pageSize=12;//=numPerPage=>페이지당 보여주는 게시물수 
		int blockSize=3;//=pagePerBlock=>블럭당 보여주는 페이지수
		
	//게시판을 맨 처음 실행시키면 무조건 1페이지부터 출력->가장 최근의 글부터 출력
	String pageNum=request.getParameter("pageNum");
	if(pageNum==null){
		pageNum="1";//default(모조건 처음에는 1페이지부터)
	}
	int currentPage=Integer.parseInt(pageNum);//"1"->1(nowPage)->현재 페이지
	//					 (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21  페이지마다 시작 레코드번호
	int startRow=(currentPage-1)*pageSize+1;//시작 레코드번호
	int endRow=currentPage*pageSize;//1*10,2*10=20,3*10=30

	int count=0;//총레코드수
	int number=0;//beginPerPage(페이지별로 맨처음에 나오는 게시물번호)
	List<MateDTO> articleList=null;//화면에 출력할 레코드를 저장할 변수

	MateDAO dbPro=new MateDAO();
	count=dbPro.getArticleCount();//select count(*) from friend;
	System.out.println("현재 레코드수(count)=>"+count);
	if(count >0) {						 //첫번째레코드번호, 불러올갯수
		articleList=dbPro.getArticles(startRow, pageSize);
		System.out.println("ListAction의 articleList=>"+articleList);
	}
	//				122-(1-1)*10=122-0=122,121,120,119,,,,
	//				122-(2-1)*10=122-10=112,111,110,,,,
	number=count-(currentPage-1)*pageSize;
	System.out.println("페이지별로 number=>"+number);
	
	//2.처리한 결과를 공유(서버메모리에 저장)=>이동할페이지에 공유(request)
	request.setAttribute("currentPage", currentPage);//${키명} 쓰기때문에 키값이랑 같게하면 좋다
	request.setAttribute("startRow", startRow);//시작레코드 번호
	request.setAttribute("count", count);//총레코드수
	request.setAttribute("pageSize", pageSize);//페이지당 10개
	request.setAttribute("blockSize", blockSize);//블럭당 5페이지
	request.setAttribute("number", number);//페이지당 시작게시물번호
	request.setAttribute("articleList", articleList);//출력할 레코드값들 ${articleList}
	
	//3.공유해서 이동할 수 있도록 페이지를 지정
		return "/list.jsp";//컨트롤러가 이동시키면서 공유시켜준다.=>view
	}

}
