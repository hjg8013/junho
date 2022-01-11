<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet">
<link href="../resources/css/all.css" rel="stylesheet" type="text/css">
<link href="../resources/css/sb-admin-2.css" rel="stylesheet">
<link href="../resources/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../resources/js/tables.js" type="text/javascript"></script>

</head>
<body>
<h1>게시판 목록 리스트</h1>
	<div class="card-body">
	    <div class="table-responsive">
	        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	            <thead>
	                <tr>
	                    <th>게시판 번호</th>
	                    <th>제목</th>
	                    <th>작성자</th>
	                    <th>작성일</th>
	                    <th>조회수</th>
	                    <th>좋아요</th>	
	                </tr>
	            </thead>
	            <tfoot>
	                <tr>
	                    <th>Name</th>
	                    <th>Position</th>
	                    <th>Office</th>
	                    <th>Age</th>
	                    <th>Start date</th>
	                    <th>Salary</th>
	                </tr>
	            </tfoot>
	            <tbody>
	            <c:forEach items="${tables}" var="board">
	                <tr>
	                    <td>${board.bno}</td>
	                    <td><a href="datail?bno=${board.bno}">${board.title}</a></td>
	                    <td>${board.writer}</td>
	                    <td>${board.regdate}</td>
	                    <td>${board.cnt}</td>
	                    <td>${board.good}</td>
	                </tr>
	            </c:forEach>
	            </tbody>
	        </table>
	        <form id="actionForm" action="tables" method="get">
		        <div class="form-control bg-Light border-0 small">
			            <input type="text" name="pageNum" value="${pageMaker.cri.pageNum}">
			            <!-- hidden을 사용하여 가릴수가 있다 -->
                     	<input type="text" name="amount" value="${pageMaker.cri.amount}">
			            <select name="search">
			            	<option value="T">제목</option>
			            	<option value="C">내용</option>
			            	<option value="W">작성자</option>
			            	<option value="TC">제목 + 내용</option>
			            	<option value="TCW">제목 + 내용 + 작성자</option>
			            </select>
			            <input type="text" name ="keyword">
			            <input type="submit" value="검색">
		         </div>
		         <div id="dataTable_paginate" class="dataTables_paginate paging_simple_numbers">
                     <ul class="pagination">
                        <c:if test="${pageMaker.prev}">
                         <li class="paginate_button page-item previous" id="dataTable_previous">
                            <a href="${pageMaker.startPage-1}" class="page-link">이전</a>
                         </li>
                      </c:if>
                      <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                         <li class="paginate_button page-item">
                            <a href="${num}" class="page-link">${num}</a>
                            <!-- <a href="tables?pageNum=${num}&amount=${pageMaker.cri.amount}" class="page-link">${num}</a> -->
                         </li>
                      </c:forEach>
                      <c:if test="${pageMaker.next}">
                         <li class="paginate_button page-item next" id="dataTable_next">
                            <a href="${pageMaker.endPage+1}" class="page-link">다음</a>
                         </li>
                      </c:if>
                     </ul>
                </div>
	        </form>
	    </div>
	</div>
</body>
</html>