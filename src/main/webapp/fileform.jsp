<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>     

<%
	String filename=request.getParameter("filename");
	filename="images.jpcg";
%>
		<form action="fileFormOK.jsp" method="post" enctype="multipart/form-data">
		    
				파일:<input type="file" name="file"><br/>        
				
					<input type="submit" value="file upload"> 
				
		</form> 

</body>
</html>
