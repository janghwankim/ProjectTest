package Kjh.board;


public class TendencyDTO {

	private int id_no = 0;// 아이디일련번호
	private String workStartTime = "";
	private String workEndTime = "";
	private String sleeptime = ""; //수면시간
	private String smoking = "";// 흡연유무
	private String pet = ""; //반려동물유무
	private String cleaning = "";//관리스타일
	private String sleepinghabbit = "";//잠버릇
	private String showertime = "";//샤워시간
	
	//필터를 위해 추가 
	private String searchText = "";
	private String sleep ="" ;	
	
	
	
	public String getSleep() {
		return sleep;
	}
	public void setSleep(String sleep) {
		this.sleep = sleep;
	}
	public String getWorkStartTime() {
		return workStartTime;
	}
	public void setWorkStartTime(String workStartTime) {
		this.workStartTime = workStartTime;
	}
	public String getWorkEndTime() {
		return workEndTime;
	}
	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}

	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getId_no() {
		return id_no;
	}
	public void setId_no(int id_no) {
		this.id_no = id_no;
	}
	public String getSleeptime() {
		return sleeptime;
	}
	public void setSleeptime(String sleeptime) {
		this.sleeptime = sleeptime;
	}
	public String getSmoking() {
		return smoking;
	}
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}
	public String getPet() {
		return pet;
	}
	public void setPet(String pet) {
		this.pet = pet;
	}
	public String getCleaning() {
		return cleaning;
	}
	public void setCleaning(String cleaning) {
		this.cleaning = cleaning;
	}
	public String getSleepinghabbit() {
		return sleepinghabbit;
	}
	public void setSleepinghabbit(String sleepinghabbit) {
		this.sleepinghabbit = sleepinghabbit;
	}
	public String getShowertime() {
		return showertime;
	}
	public void setShowertime(String showertime) {
		this.showertime = showertime;
	}
	
	
	
	
	
}
