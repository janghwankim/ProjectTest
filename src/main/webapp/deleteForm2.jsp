<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">      
   
  function deleteSave(){	
	if(document.delForm.passwd.value==''){
	alert("비밀번호를 입력하십시요.");
	document.delForm.passwd.focus();
	return false;
 }
}       
</script>
</head>

<body bgcolor="#e0ffff">
<center><b>글삭제</b>
<br>
<form method="POST" name="delForm"  
			action="/Project/deletePro.do?pageNum=${pageNum}&mate_no=${mate_no}" 
   			onsubmit="return deleteSave()"> 
 <table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
  <tr height="30">
     <td align=center  bgcolor="#b0e0e6">
       <b>비밀번호를 입력해 주세요.</b></td>
  </tr>
  <tr height="30">
     <td align=center >비밀번호 :   
       <input type="password" name="password" size="8" maxlength="12">
	   <%-- 
	   <input type="hidden" name="num" value="${num}">
	   <input type="hidden" name="pageNum" value="${pageNum}"> 
	   --%>
	 </td>
 </tr>
 <tr height="30">
    <td  height="80" align="center">
       	 	<input class="btn btn-outline-secondary" type="submit" value="삭제하기">  
        	<input class="btn btn-outline-secondary" type="button" value="목록보기" onclick="document.location.href='/Project/list.do?pageNum=${pageNum}'">
        </td>
 </tr>  
</table> 
</form>
</body>
</html> 
