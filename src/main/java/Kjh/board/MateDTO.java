package Kjh.board;


import java.sql.Timestamp;//DB상의 테이블의 필드와 연관


public class MateDTO {

	
	private int mate_no;//룸메이트게시판번호
	private int id_no;// 아이디일련번호
	private String writer; //작성자
	private String title; //제목
	private String gender; //성별
	private String created_datetime;// 작성일
	private String lifestyle; //라이프스타일
	private int views;//조회수
	private String other_matter; //기타사항
	
	

	public int getMate_no() {
		return mate_no;
	}
	public void setMate_no(int mate_no) {
		this.mate_no = mate_no;
	}
	public int getId_no() {
		return id_no;
	}
	public void setId_no(int id_no) {
		this.id_no = id_no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = convert(title);
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCreated_datetime() {
		return created_datetime;
	}
	public void setCreated_datetime(String created_datetime) {
		this.created_datetime = created_datetime;
	}
	public String getLifestyle() {
		return lifestyle;
	}
	public void setLifestyle(String lifestyle) {
		this.lifestyle = convert(lifestyle);
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getOther_matter() {
		return other_matter;
	}
	public void setOther_matter(String other_matter) {
		this.other_matter = convert(other_matter);
	}


	//모든 DTO클래스 뒤에 이 메서드를 추가할것
		//이 클래스에서만 사용하기위해서 접근지정자 private <,>,(,)=>변경메서드
			private static String convert(String name) {
				if(name!=null){
			    	//2.입력받은 문자열중에서 자바스크립트 구문을 실행시킬수 있는 특수기호를 입력X(<,>)
			    	//문자열메서드->replaceAll(1.변경전문자열,2.변경후 문자열)
			    	
			    	name=name.replaceAll("<","&lt");
			    	name=name.replaceAll(">","&gt");
			    	//추가 eval(" " or ' ')
			    	name=name.replaceAll("\\(","&#40");
			    	name=name.replaceAll("\\)","&#41");
			    	//"test"  'test'
			    	name=name.replaceAll("\"","&quot");
			    	name=name.replaceAll("\'","&apos");
			    }else{ //name==null
			    	return null; //입력을 하지 않았다면 더 이상 실행X
			    }
				return name;
			}

	
	
	
}
