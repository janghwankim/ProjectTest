<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="memMgr" class="krh.member.MemberDAO" />

<%
	//Login.jsp->LoginProc.jsp -> LoginSuccess.jsp
	String id=request.getParameter("id");
	String password=request.getParameter("password");
	
	System.out.println("id=>"+id+",password=>"+password);
	//MemberDAO 객체 필요-> loginCheck() 호출
	//MemberDAO memMgr=new MemberDAO();
	boolean check=memMgr.loginCheck(id, password);	
%>
<%
//check->LoginSuccess.jsp(인증화면),LogError.jsp(에러페이지)
if(check){//if(check==true)인증성공
	session.setAttribute("idKey", id);
	//response.sendRedirect("LoginSuccess.jsp");//단순페이지이동
	response.sendRedirect("Login.jsp");
}else{
	response.sendRedirect("LogError.jsp");
	
}

%>