<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<title>roommate_add</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
  <!-- Bootstrap icons-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="css/bootstrap.css" rel="stylesheet" /> 
  <link href="css/styles.css" rel="stylesheet" />
<script language="JavaScript" src="script.js?ver=1"></script>
</head>
   <%
	//int num=(Integer)request.getAttribute("num");//=>${num}
   %>
<body>  
<div class="wrap">
            <!-- 로고 -->
            <header class="hd">
                <div class="logo">
                    <a href="#"><img src="image/logo.png"></a>
                </div>
            </header>
            <!-- 메뉴바 -->
            <nav class="nav">
                <ul class="menu">
                    <li><a href="#">쉐어하우스</a></li>
                    <li><a href="#">룸메이트</a></li>
                    <li><a href="#">자유게시판</a></li>
                    <li><a href="#">공지사항</a></li>
                    <li><a href="#">성향테스트</a></li>
                </ul>
            </nav>
            <p>
	<div>
	<form method="post" name="writeform" 
		   action="/Project/writePro.do" 
		   onsubmit="return writeSave()">
		   
	<!-- 입력하지 않고 매개변수로 전달해서 테이블에 저장(hidden) -->		   
	<input type="hidden" name="mate_no" value="${num}"> 
	<input type="hidden" name="id_no" value="1">
	<input type="hidden" name="views" value="0">
	

		<div class="pf_session">
                <div class="pf">  
                    <div class="pf_top">
                        <div class="pf_img"> 
                            <!-- 이미지 -->
                             <input type="file" class="real-upload" accept="image/*" required multiple>
                        </div> 
                    </div>
                    <div class="pf_body">
                            <table width="770" border="1" cellspacing="0" cellpadding="0" align="center">
                                <tr>
                                    <td bgcolor="#F5F5F5"><b>작성자</b></td>
                                    <td>
                                        <input type="text" class="pf_input" name="writer" >
                                    </td>
                                    <td bgcolor="#F5F5F5"><b>성별</b></td>
                                    <td>
                                        <input type="text" class="pf_input" name="gender">
                                    </td>
                                </tr>
                                <tr>
                                    <td bgcolor="#F5F5F5"><b>제목</b></td>
                                    <td colspan="3">
                                        <input type="text" class="pf_input" name="title">
                                    </td>
                                 
                                </tr>
                                <tr>
                                    <td bgcolor="#F5F5F5"><b>성향</b></td>
                                    <td colspan="3">
                                        <input type="text" class="pf_input" style="width: 80%;"  >
                                    </td>
                                </tr>
                                <tr>
                                    <td bgcolor="#F5F5F5"><b>라이프스타일</b></td>
                                    <td colspan="3">
                                        <input type="text" class="pf_input" name="lifestyle">
                                    </td>
                                </tr>
                                <tr>
                                    <td bgcolor="#F5F5F5"><b>기타사항</b></td>
                                    <td colspan="3">
                                        <input type="text" class="pf_input" name="other_matter">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="center">
                                    <input class="btn btn-outline-secondary" type="reset" value="다시작성">  
                                    <input class="btn btn-outline-secondary" type="submit" value="생성하기">
                                    <input class="btn btn-outline-secondary" type="button" value="목록보기" OnClick="window.location='/Project/list.do'">
                                    </td>
                                </tr>
                            </table>
                    </div>
             </div>
            </div>
          </form>  
          </div>
        <!-- 꼬리말 -->
        <footer class="ft">
            <nav class="nav2">
                <ul>
                    <li><a href="#">서비스 이용약관</a></li>
                    <li class="l1">l</li>
                    <li><a href="#">개인정보처리 방침</a></li>
                    <li class="l1">l</li>
                    <li><a href="#">광고 문의</a></li>
                    <li class="l1">l</li>
                    <li><a href="#">고객서비스 센터</a></li>
                    <li class="l1">l</li>
                    <li><a href="#">위치기반 서비스 이용약관</a></li>
                </ul>
            </nav>
            <br>
            <p class="p">
                <a>상호 : (주)위드룸&nbsp;|&nbsp;주소 : 서울특별시 강남구 테헤란로1길 10&nbsp;|&nbsp;팩스 : 02-123-4567</a>
                <br>
                <a>서비스 이용문의 : 1234-9876&nbsp;|&nbsp;이메일 : cs@wedroom.com</a>
            </p>
        </footer>
      
</div>
</body>
</html>      
