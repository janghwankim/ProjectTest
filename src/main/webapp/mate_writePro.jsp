<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.Timestamp,Kjh.board.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="Kjh.board.MateDTO"%>
<%@page import="Kjh.board.MateDAO"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
    
<%
	//response.sendRedirect("list.jsp");//입력한후 다시 DB접속->출력
%>   
<meta http-equiv="Refresh" content="0;url=/Project/mate_list.do">