<%@page import="Kjh.board.FileTestDTO"%>
<%@page import="Kjh.board.MateDAO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%

	String directory="C:/webtest/4.jsp/sou/Project/src/main/webapp/fileFolder"; //저장될 폴
    int maxsize = 1024 * 1024 * 100; 
	String encoding="UTF-8";
    
  
		FileTestDTO filetest=new FileTestDTO();
		
		try{
	        MultipartRequest multi = new MultipartRequest(request,directory,maxsize,encoding,new DefaultFileRenamePolicy());
			String filename=multi.getOriginalFileName("file");
			String fileRealname=multi.getFilesystemName("file");
			
			new MateDAO().upload(filename, fileRealname);
			out.write("dir=>"+directory);
	        out.write("파일명: "+filename+"<br>");
	        out.write("실제파일명: "+fileRealname+"<br>");
		%>
		<img src="<%=request.getContextPath() %>/fileFolder/<%=filename %>">
		<%
	    }catch(Exception e)
	
	    {
	
	        e.printStackTrace();
	
	    }

%>

    
    
    
    
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	file upload success!
</body>
</html>