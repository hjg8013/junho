<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="./resources/js/uploadAjax.js"></script>
</head>
<body>
<div>
      <input type="file" name="uploadFile" multiple> 
      <!-- multiple을 추가하면 여러파일을 선택가능하다 name의 이름과 controller의 변수이름이 같아야한다 -->
</div>
<input type="submit" value="전송">

</body>
</html>