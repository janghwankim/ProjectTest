package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//추가
import Kjh.board.*;

public class ContentAction implements CommandAction {

	private Connection con = null;
	private DBConnectionMgr pool = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;// select를 해서 찾은값을 담는 상자라고 생각하기
	private String sql = "";// 실행시킬 SQL구문
	private HttpSession session;
	
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

		//----------------------------------------------------------------------------------
		//성향부분
		
		   pool = DBConnectionMgr.getInstance();
		   con=pool.getConnection();
		   sql = "select * from friend a inner join tendency b on a.id_no = b.id_no where mate_no=?";
		   pstmt = con.prepareStatement(sql);
		   pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			String sleeptime= "";
			String waketime = "";
			String smoking ="";
			String pet ="";
			String sleepinghabbit ="";
			String showertime ="";
			String start_time ="";
			String end_time ="";
			if (rs.next()) {
				sleeptime = rs.getString("sleeptime");
				waketime = rs.getString("waketime");
				smoking = rs.getString("smoking");
				pet = rs.getString("pet");
				sleepinghabbit = rs.getString("sleepinghabbit");
				showertime = rs.getString("showertime");
				start_time = rs.getString("start_time");
				end_time = rs.getString("end_time");
			}
		//------------------------------------------------------------------------------------

		
		
		
		
		//2.실행결과 서버메모리에 저장
		request.setAttribute("num", num);//${키명}때문에 키명이랑 value값을 같게 설정함
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
	
	   	//Session 성향부분 추가
	   	request.setAttribute("sleeptime", sleeptime);
	   	request.setAttribute("waketime", waketime);
	   	request.setAttribute("smoking", smoking);
	   	request.setAttribute("pet", pet);
	   	request.setAttribute("sleepinghabbit", sleepinghabbit);
	   	request.setAttribute("showertime", showertime);
	   	request.setAttribute("start_time", start_time);
	   	request.setAttribute("end_time", end_time);
		
		//3.페이지 공유
		return "/content.jsp"; //경로에 맞게 설계  
	}

}
