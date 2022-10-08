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

<script>
function commonChk(chk,cmd) {
	if(chk.checked)
		document.getElementById(cmd).value="Y";
	else
		document.getElementById(cmd).value="N";
}

function more(){
	alert('더보기')
}

</script>
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
        <form name="filter" class="filter" action="/Project/list.do" method="post" onsubmit="doSearch();">
        <select class="form-select form-select-lg" name="workStartTime">
            <option value="" selected>출근시간</option>
            <option value="8">08:00</option>
            <option value="9">09:00</option>
            <option value="10">10:00</option>
            <option value="11">11:00</option>
            <option value="others">저녁출근</option>
        </select>
            <select class="form-select form-select-lg" name="workEndTime">
                <option value=""  selected>퇴근시간</option>
                <option value="17">17:00</option>
                <option value="18">18:00</option>
                <option value="19">19:00</option>
                <option value="20">20:00</option>
                <option value="etc">기타</option>
            </select>
            <select class="form-select form-select-lg" name="sleepTime">
                <option value=""  selected>수면시간</option>
                <option value="22">22:00</option>
                <option value="23">23:00</option>
                <option value="24">24:00</option>
                <option value="1">01:00</option>
                <option value="2">02:00</option>
            </select>
            <select class="form-select form-select-lg" name="showerTime">
                <option value=""  selected>샤워시간</option>
                <option value="am">오전</option>
                <option value="pm">오후</option>
            </select>
            <p>
                 <div class="form-check">
                    <input class="form-check-input" type="checkbox" onclick="commonChk(this,'smokeYN')" id="c_box1" >
                    <input type="hidden" name="smokeYN" id="smokeYN">
                    <label class="form-check-label" for="c_box1">
                      흡연유무
                    </label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="c_box2" onclick="commonChk(this,'petYN')" >
                    <input type="hidden" name="petYN" id="petYN">
                    <label class="form-check-label" for="c_box2">
                      반려동물유무
                    </label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="c_box3" onclick="commonChk(this,'sleepYN')" >
                    <input type="hidden" name="sleepYN" id="sleepYN">
                    <label class="form-check-label" for="c_box3">
                      잠버릇유무
                    </label>
                  </div>
                  <div class="form-check" style="right: 14px;">
                    <input class="btn btn-outline-secondary" type="submit" value="검색하기">
                   <!--  <input type="text" class="searchText" name="searchText" style=""/> -->
                  </div>
                  
             	</form> 
            </div>
            
            
		<!-- 데이터의 유무 -->
		<c:if test="${pgList.count==0 }">

	     	<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
	     	<tr>
				<td class="add_bt" align="right">
               	 	<input class="btn btn-outline-secondary" type="submit" value="새 글 작성"  OnClick="window.location='/Project/writeForm.do'">
            	</td>
			</tr>
			<tr>
				<td align="center">게시판에 저장된 글이 없습니다.</td>
			</tr>
		</table>
		</c:if>

 		<c:if test="${pgList.count > 0 }">
        <!-- Section-->
        <section class="py-5">
            <span class="add_bt">
                <input class="btn btn-outline-secondary" type="submit" value="새 글 작성"  OnClick="window.location='/Project/writeForm.do'">
            </span>

            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
          		
          		 <c:set var="number" value="${pgList.number}" />
                <c:forEach var="article" items="${articleList}">
                
                    <div class="col mb-5">
                        <div class="card h-100" onclick="location.href='/Project/content.do?num=${article.mate_no}&pageNum=${pgList.currentPage}'">
                            <!-- Product image-->
                          <div class="col_up">
                            <div class="card_pf" style="
                                background:url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSc2M6fCVIQhRBDQQj9EZloPHSglbtpYjW0bg&usqp=CAU') center center">
                            </div>
                          </div>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${article.title}</h5>
                                    <!-- Product price-->
                                    ${article.writer}
                                </div>
                                <span class="mini_s">비흡연</span>  <span class="mini_s">반려동물</span>
                            </div>
                        </div>
                    </div>
                        
                </c:forEach> 
      
                </div>
            </div>  
            
            <%-- <hr>
            <center>
      			<input class="btn btn-outline-secondary" id="more" type="button" value="더보기" onclick="more()">
      		</center> --%>
        </section>
        </c:if>


 <!-- 페이징 처리 -->
	<center>
	<c:if test="${pgList.startPage > pgList.blockSize}">
		<a href="/Project/list.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}"> [이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
		<a href="/Project/list.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
			<c:if test="${pgList.currentPage==i}">
				<font color="red"><b>[${i}]</b></font>
			</c:if>	
			<c:if test="${pgList.currentPage!=i}">
				${i}
			</c:if>	
		</a>
	</c:forEach>
	
	<c:if test="${pgList.endPage <pgList.pageCount}">
		<a href="/Project/list.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}"> [다음]</a>
	</c:if>
	</center>
<p>

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