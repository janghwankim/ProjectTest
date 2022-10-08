package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//list.jsp(글쓰기)->신규글,content.jsp(글상세보기->답변글)
		   int num=0;
		   
		   //content.do에서 매개변수로 전달
		   if(request.getParameter("num")!=null){//0,음수X=>양수1이상
			   num=Integer.parseInt(request.getParameter("num"));//"3"=>3

			   System.out.println("content.jsp에서 넘어온 매개변수 확인");
			   System.out.println("num=>"+num);
				
		   }
		   //2.실행결과->서버의 메모리에 저장=>공유해서 이동
		   	request.setAttribute("num", num); //${num}
		   			   
		return "/writeForm.jsp";
	}

}
