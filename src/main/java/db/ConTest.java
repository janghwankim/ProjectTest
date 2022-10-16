package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConTest
 */
@WebServlet("/ConTest")
public class ConTest extends HttpServlet {
	
	//id,passwd,file
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//입력
		String id=request.getParameter("id");
		String pwd="";
		String decrypt=request.getParameter("decrypt");
		//Properties파일에서 불러올 키값을 변수선언
		String _driver,_url,_user,_password;
		
		if(pwd==null || "".equals(pwd)) {
		
		try {
			
			//c:\webtest\10.secure\test
			String location="C:/webtest/10.secure/test/dboracle.properties";
			String location2="C:/webtest/10.secure/test/"+decrypt;//암호파일
			System.out.println("location2=>"+location2);
			
			//1)DB파일설정파일->암호화시켜서 암호화파일 생성
			Crypt.encryptFile(location, location2);//원본파일명,생성할암호화파일명
			/*
			//2.원본파일 지우기
			File f=new File(location);
			f.delete();//원본파일 삭제
			*/
			//3.복호화 시키기 위한 암호화된 파일 불러오기
			//C:\webtest\4.jsp\sou\WebSecure2\src\main\webapp\back
			Crypt.decryptFile(location2, 
					"C:\\webtest\\4.jsp\\sou\\WebSecure2\\src\\main\\webapp\\back\\jdbc22.properties");
			
			//4.복호화된 파일->Properties객체를 생성->메모리에 올려서 각키불러오기		
			Properties props=new Properties();
	    	//원본파일->암호와
	    	
	    	//암호화->복원화
	    	FileInputStream in=new FileInputStream
	    	("C:\\webtest\\4.jsp\\sou\\WebSecure2\\src\\main\\webapp\\back\\jdbc22.properties");
	    	props.load(in);//파일의 내용을 메모리에 불러오기
	    	in.close();
	    	_driver=props.getProperty("jdbc.drivers");
	    	//드라이브만 시스템에 반영
	    	if(_driver!=null)
	    		System.setProperty("jdbc.drivers",_driver);//등록
	    	_url=props.getProperty("jdbc.url");
	    	_user=props.getProperty("jdbc.username");
	    	_password=props.getProperty("jdbc.password");
	    	System.out.println("_driver=>"+(_driver)+",_url=>"+(_url));
	    	System.out.println("_user=>"+(_user)+",_password=>"+(_password));
	    	//---------------------------------------------------------------------------------------------
	    	//DB연결테스트
	    	Connection con=DBConnect(_url,_user,_password);
	    	if(con!=null) {
	    		out.println("DB접속에 성공했습니다.=>"+con);
	    	}
	    	//---------------------------------------------------------------------------------
				}catch(Exception e) {
					e.printStackTrace();
			}
		}	
	} //doPost()
	
	public static Connection DBConnect(String _url,String _user,String _password){
		Connection conn=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(_url,_user,_password);
			System.out.println("conn="+conn);
		}catch(Exception e){
			System.out.println("DB연결실패="+e);
		}
		return conn;
	}
}

