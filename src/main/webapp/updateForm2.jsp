<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>글수정하기</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<body bgcolor="#e0ffff">  
<center><b>글수정</b>
<br>
<form method="post" name="writeform" 
			action="/Project/updatePro.do" 
			onsubmit="return writeSave()">
			
<table width="400" border="1" cellspacing="0" cellpadding="0"  bgcolor="#e0ffff" align="center">
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center">이 름</td>
    <td align="left" width="330">
       <input type="text" size="10" maxlength="10" name="writer" value="${article.writer}">
	   <input type="hidden" name="mate_no" value="${article.mate_no}"><!-- post방식이라 hidden으로 넘김 -->
<%--  <input type="hidden" name="pageNum" value="${pageNum}">  --%>
		 
	</td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >제 목</td>
    <td align="left" width="330">
       <input type="text" size="40" maxlength="50" name="title" value="${article.title}"></td>
  </tr>
  
  

  <tr>      
   <td colspan=2 bgcolor="#b0e0e6" align="center"> 
     <input type="submit" value="글수정" >  
     <input type="reset" value="다시작성">
     <input type="button" value="목록보기" 
       onclick="document.location.href='/Project/list.do?pageNum=${pageNum}'">
   </td>
 </tr>
 </table>
</form>     
</body>
</html>      
