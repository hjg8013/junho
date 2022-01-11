/**
 * 
 */
// 현재페이지 번호를 클릭하면
// form태그 안에 있는 값을 가져와라

$(document).ready(function(){
	var actionForm = $("#actionForm");
	
	$(".paginate_button a").on("click",function(e){
		//클륵을 했을때 안넘어가게 해준다 a 태그의 고유속성을 막아주는 역할을 한다
		e.preventDefault();
		
		actionForm.find("input[name='pageNum']").val($(this).attr("href"))
		//내가 선택한 href의 값을 가져와라
		/* <input type="text" name="pageNum" value="${pageMaker.cri.pageNum}">
		 * num에 pageNum를 넣어주는 역할을 한다
		 * <li class="paginate_button page-item">
             <a href="${num}" class="page-link">${num}</a>           
           </li>
           
           
           <input type="text" name="pageNum" value="${pageMaker.cri.pageNum}">
               <input type="text" name="amount" value="${pageMaker.cri.amount}">
		 * */
		actionForm.submit();
		
	});
	$("input[type='submit']").on("click",function(e){
		e.preventDefault(); //구현하는거에는 필요가없고 테스트하는용도로 적어뒀다
		//peageNum의 값을 1로 실행
		actionForm.find("input[name='pageNum']").val("1")
		actionForm.submit();
	});
})