<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
<title>roommate board</title>
  <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
  <!-- Bootstrap icons-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="css/bootstrap.css" rel="stylesheet" /> 
  <link href="css/styles.css" rel="stylesheet" />
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
       <div class="op">
        <form name="filter" class="filter" action="" method="post">
        <select class="form-select form-select-lg">
            <option selected>출근시간</option>
            <option value="time1">08:00</option>
            <option value="time2">09:00</option>
            <option value="time3">10:00</option>
            <option value="time4">11:00</option>
            <option value="time5">저녁출근</option>
        </select>
            <select class="form-select form-select-lg">
                <option value="selected">퇴근시간</option>
                <option value="time1">17:00</option>
                <option value="time2">18:00</option>
                <option value="time3">19:00</option>
                <option value="time4">20:00</option>
                <option value="time5">기타</option>
            </select>
            <select class="form-select form-select-lg">
                <option value="selected">수면시간</option>
                <option value="time1">22:00</option>
                <option value="time2">23:00</option>
                <option value="time3">24:00</option>
                <option value="time4">01:00</option>
                <option value="time5">02:00</option>
            </select>
            <select class="form-select form-select-lg" >
                <option value="selected">샤워시간</option>
                <option value="time1">오전</option>
                <option value="time2">오후</option>
            </select>
            <p>
                 <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="c_box1" >
                    <label class="form-check-label" for="c_box1">
                      흡연유무
                    </label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="c_box2" >
                    <label class="form-check-label" for="c_box2">
                      반려동물유무
                    </label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="c_box3" >
                    <label class="form-check-label" for="c_box3">
                      잠버릇유무
                    </label>
                  </div>
                  <div class="form-check" style="right: 14px;">
                    <input class="btn btn-outline-secondary" type="submit" value="검색하기">
                  </div>
             	</form> 
            </div>
            
       <center><b>글목록(전체 글:${pgList.count})</b>
<table width="700">
<tr>
    <td align="right" bgcolor="#b0e0e6">
    <a href="/Project/writeForm.do">글쓰기</a>
    </td>
 </tr>   
</table>
<!-- 데이터의 유무 -->
<c:if test="${pgList.count==0 }">
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td align="center">게시판에 저장된 글이 없습니다.</td>
	</tr>
</table>
</c:if>

<c:if test="${pgList.count > 0 }">
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="#b0e0e6"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
    </tr>
    <!-- 실질적으로 레코드를 출력시켜주는 부분 
    		this(현재 마우스를 갖다댄 객체(tr)을 의미-->
    	<tbody>		
    <c:set var="number" value="${pgList.number}" />
    <c:forEach var="article" items="${articleList}">
   <tr height="30" onmouseover="this.style.backgroundColor='white'"
   							 onmouseout="this.style.backgroundColor='#e0ffff'">
   <!-- 하나씩 감소하면서 출력하는 게시물번호 -->
    <%-- <td align="center"  width="50" >
    <c:out value="${number}" />
    <c:set var="number" value="${number-1}" /> <!-- number--을 하기위해 c:set을 한번 더 써서 수정의 의미로 사용 -->
    </td> --%>
    <td>${article.mate_no}</td>
    <td  width="250" >
      <a href="/Project/content.do?num=${article.mate_no}&pageNum=${pgList.currentPage}">${article.title}</a> 
     </td>
    <%-- <td align="center"  width="100"> 
       <a href="mailto:${article.email}">${article.writer}</a></td>
    <td align="center"  width="150"> --%>
    	<td><fmt:formatDate value="${article.created_datetime}" timeStyle="medium" pattern="yy.MM.dd (hh:mm)" />
    </td>
    <td align="center"  width="50">${article.views}</td>
  </tr>
  </c:forEach>
  </tbody>
</table>
</c:if>

<%-- <!-- 페이징 처리 -->

	<c:if test="${pgList.startPage > pgList.blockSize}">
		<a href="/JspBoard2/list.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}"> [이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
		<a href="/JspBoard2/list.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
			<c:if test="${pgList.currentPage==i}">
				<font color="red"><b>[${i}]</b></font>
			</c:if>	
			<c:if test="${pgList.currentPage!=i}">
				${i}
			</c:if>	
		</a>
	</c:forEach>
	
	<c:if test="${pgList.endPage <pgList.pageCount}">
		<a href="/JspBoard2/list.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}"> [다음]</a>
	</c:if>
 --%>
<p>
<!-- 
	검색어 추가(자주 검색이 되는 항목을 선택) 제목,작성자,제목+본문
	ex) search.do=>새로운 요청명령어를 이용해서 추가하거나
		  list.do-> 기존의 내용을 추가
		  검색분야=>필드명과 일치하게 이름을 작성->검색을 편하게 작업
 -->
 <form name="test" action="/Project/list.do">
 	<select name="search">
 		<option value="subject">제목</option>
 		<option value="subject_content">제목+본문</option>
 		<option value="writer">작성자</option>
 	</select>
 	<input type="text" size="15" name="searchtext">&nbsp;
 	<input type="submit" value="검색"> 
 </form>
</center>
        
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