package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Kjh.board.DBConnectionMgr;
import Kjh.board.MemberDTO;
import Kjh.board.TendencyDTO;

public class mate_WriteFormAction implements CommandAction {

	private Connection con = null;
	private DBConnectionMgr pool = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;// select를 해서 찾은값을 담는 상자라고 생각하기
	private String sql = "";// 실행시킬 SQL구문
	private HttpSession session;
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		   int num=0;

		   //Session의 아이디와 성별을 가져불러와 적용하기위한 부분
		   String loginUserID = "kkk";		//임시로 넣어둔 값(실제 DB에 있는 아이디) (나중에 밑어껄로 바꿔야함)
// 		   String loginUserID = session.getAttribute("id");   					 //Session을 Object로 담았을때
//		   String loginUserID = session.getAttribute("id").toString();		 //Session을 String으로 담았을때
//		   MemberDTO mem = (MemberDTO)session.getAttribute("loginUserInfo"); //Session이 DTO로 담았을때
//		   loginUserID = mem.getId();
		   pool = DBConnectionMgr.getInstance();
		   con=pool.getConnection();
		   sql = "SELECT A.ID, A.GENDER, B.* FROM MEMBER A "
		   		+ "INNER JOIN TENDENCY B "
		   		+ "ON A.ID_NO = B.ID_NO"
		   		+ " where A.id='"+loginUserID +"'";
		   
		   pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String gender= "";
			String id = "";
			
		//	int id_no= 0;
			String sleeptime= "";
			String waketime = "";
			String smoking ="";
			String pet ="";
			String sleepinghabbit ="";
			String showertime ="";
			String start_time ="";
			String end_time ="";
			if (rs.next()) {
				gender = rs.getString("gender");
				id = rs.getString("id");
				
	//			id_no=rs.getInt("id_no");
				sleeptime = rs.getString("sleeptime");
				waketime = rs.getString("waketime");
				smoking = rs.getString("smoking");
				pet = rs.getString("pet");
				sleepinghabbit = rs.getString("sleepinghabbit");
				showertime = rs.getString("showertime");
				start_time = rs.getString("start_time");
				end_time = rs.getString("end_time");
			}
		
		   //content.do에서 매개변수로 전달
		   if(request.getParameter("num")!=null){//0,음수X=>양수1이상
			   num=Integer.parseInt(request.getParameter("num"));//"3"=>3

			   System.out.println("content.jsp에서 넘어온 매개변수 확인");
			   System.out.println("num=>"+num);
				
		   }
		   //2.실행결과->서버의 메모리에 저장=>공유해서 이동
		   	request.setAttribute("num", num); //${num}
		   	request.setAttribute("id", id); //${id}
		   	request.setAttribute("gender", gender); //${gender}

//		   	request.setAttribute("id_no", id_no);
		   	request.setAttribute("sleeptime", sleeptime);
		   	request.setAttribute("waketime", waketime);
		   	request.setAttribute("smoking", smoking);
		   	request.setAttribute("pet", pet);
		   	request.setAttribute("sleepinghabbit", sleepinghabbit);
		   	request.setAttribute("showertime", showertime);
		   	request.setAttribute("start_time", start_time);
		   	request.setAttribute("end_time", end_time);
	
		   	
		   	
		return "/mate_writeForm.jsp";
	}

}
