package vo;

public class MovieBooKingVo {
	
	private int res_seat_num;
	private String user_id;
	private int movie_no;
	private int pay_type;
	
	public int getRes_seat_num() {
		return res_seat_num;
	}
	public void setRes_seat_num(int res_seat_num) {
		this.res_seat_num = res_seat_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getMovie_no() {
		return movie_no;
	}
	public void setMovie_no(int movie_no) {
		this.movie_no = movie_no;
	}
	public int getPay_type() {
		return pay_type;
	}
	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}
	@Override
	public String toString() {
		return "MovieBooKing [res_seat_num=" + res_seat_num + ", user_id=" + user_id + ", movie_no=" + movie_no
				+ ", pay_type=" + pay_type + "]";
	}
	
	
	
}
