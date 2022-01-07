<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet">
<link href="../resources/css/sb-admin-2.css" rel="stylesheet">
<link href="../resources/css/all.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>게시판 글수정</h1>
	<form action="modify" method="post">
		<div class="form-group row">
			<div class="col-sm-12 mb-3 mb-sm-0">
	        	<input type="text" value="${datail.bno}" name="bno" class="form-control form-control-user" id="exampleFirstName" placeholder="bno" readonly>
	    	</div>
	    	<!-- 사용자에게 보여줄꺼면 읽기전용 type="text" 마지막에 readonly    타입설정중에 type="hidden"으로 설정 -->
	    	<div class="col-sm-12 mb-3 mb-sm-0">
	        	<input type="text" value="${datail.title}" name="title" class="form-control form-control-user" id="exampleFirstName" placeholder="title">
	    	
	    	</div>
	    
			<div class="col-sm-12 mb-3 mb-sm-0">
				<textarea rows="10" name="content" cols="20" class="form-control form-control-user" id="exampleFirstName" placeholder="content">${datail.content}</textarea>
			</div>
			<div class="btn btn-primary btn-icon-split">
		        <input type="submit" value="글수정" class="btn btn-primary btn-icon-split">
		    </div>
	    </div>
    </form>
</body>
</html>