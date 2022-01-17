<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="uploadAction" method="post" enctype="multipart/form-data">
      <input type="file" name="uploadFile" multiple> 
      <!-- multiple을 추가하면 여러파일을 선택가능하다 name의 이름과 controller의 변수이름이 같아야한다 -->
      <input type="submit" value="전송">
   </form>
</body>
</html>