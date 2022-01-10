package org.jun.domain;

public class PageDTO {
	//전체페이지 번호
	//이전과 다음으로 이동하는 버튼
	//화면에서 보여지는 페이지의 시각 번호와 끝번호
	
	//시작페이지번호
	private int startPage;
	//끝 페이지번호
	private int endPage;
	//이전 페이지 유무
	private boolean prev;
	//다음 페이지 유무
	private boolean next;
	
	
	// board테이블의 총 데이터 건수
	private int total;
	// endpage페이지를 계산하기 위한 pagenum가 필요하므로 Criteria클래스를 포함
	private Criteria cri;
	
	
	//이렇게 두개를 추가해줘야한다
	//각각에다 10.0을 나누어주면 endPage를 계산할수 있다
	
	
	
	public PageDTO(Criteria cri, int total){
		this.cri=cri;
		this.total=total;
		//(int)(Math.ceil(현재페이지번호/10.0))*10 계산된 결과를 endPage에 저장
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10))*10;
		
		this.startPage=this.endPage-9;
		//전체건수를 고려한 endPage->realEnd
		
		//(int)(Math.ceil((전체건수*1.0)/10));
		int realEnd=(int)(Math.ceil((total*10)/cri.getAmount()));
		// realEnd < endPage  => realEnd값을
		if(realEnd<endPage) {
			this.endPage=realEnd;
		}
		this.prev=this.startPage > 1;
		this.next=this.endPage<realEnd;
	}
	
	
	
	public int getStartPage() {
		return startPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "PageDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
