<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<title>roommate_detail</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
  <!-- Bootstrap icons-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="css/bootstrap.css" rel="stylesheet" /> 
  <link href="css/styles.css" rel="stylesheet" />
<script language="JavaScript" src="script.js?ver=1"></script>
</head>
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
                <div class="pf_session">
                <div class="pf">  
                    <div class="pf_top">
                        <div class="pf_img"> 
                            
                        </div>
                    </div>  
                    <div class="pf_body">
                    <form>
                        <table width="770" border="1" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                                <td bgcolor="#F5F5F5"><b>작성자</b></td>
                                <td>${article.writer}</td>
                                <td bgcolor="#F5F5F5"><b>성별</b></td>
                                <td>${article.gender}</td>
                            </tr>
                            <tr>
                                <td bgcolor="#F5F5F5"><b>제목</b></td>
                                <td colspan="3">${article.title}</td>
                            </tr>
                            <tr>
                                <td bgcolor="#F5F5F5"><b>성향</b></td>
                                <td colspan="3">비흡연자, 반려동물, 09:00출근, 18:00퇴근</td>
                            </tr>
                            <tr>
                                <td bgcolor="#F5F5F5"><b>라이프스타일</b></td>
                                <td colspan="3"> ${article.lifestyle}</td>
                            </tr>
                            <tr height="80">
                                <td bgcolor="#F5F5F5"><b>기타사항</b></td>
                                <td colspan="3">${article.other_matter}</td>
                            </tr>
                            <tr>
                                <td bgcolor="#F5F5F5"><b>조회수</b></td>
                                <td>${article.views}</td>
                                <td bgcolor="#F5F5F5"><b>작성일</b></td>
                                <td>${article.created_datetime}</td>
                            </tr>
                            <tr>
                                <td colspan="4" align="right">
                                    <span class="pf_button">
                                        <input class="btn btn-outline-secondary" type="submit" value="신청하기">
                                    </span>
                                <input type="button" class="btn btn-outline-secondary" value="글수정"  onclick="document.location.href='/Project/updateForm.do?mate_no=${article.mate_no}&pageNum=${pageNum}'">
                                <input type="button" class="btn btn-outline-secondary" value="글삭제"  onclick="document.location.href='/Project/deleteForm.do?mate_no=${article.mate_no}&pageNum=${pageNum}'">
                                <input type="button" class="btn btn-outline-secondary" value="글목록" onclick="document.location.href='/Project/list.do?pageNum=${pageNum}'">
                                </td>
                            </tr>
                        </table>
                    </form>
                    </div> 
                </div>
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
