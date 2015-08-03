package vo;

import java.sql.Timestamp;

public class MovieInfoVo {

	private String movie_name;
	private String screen_num;
	private Timestamp movie_time;
	private int seat_amount;
	private int price;
	private int movie_no;
	private MovieBooKingVo movieBooKingVo;

	public MovieBooKingVo getMovieBooKingVo() {
		return movieBooKingVo;
	}

	public void setMovieBooKingVo(MovieBooKingVo movieBooKingVo) {
		this.movieBooKingVo = movieBooKingVo;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public String getScreen_num() {
		return screen_num;
	}

	public void setScreen_num(String screen_num) {
		this.screen_num = screen_num;
	}

	public Timestamp getMovie_time() {
		return movie_time;
	}

	public void setMovie_time(Timestamp movie_time) {
		this.movie_time = movie_time;
	}

	public int getSeat_amount() {
		return seat_amount;
	}

	public void setSeat_amount(int seat_amount) {
		this.seat_amount = seat_amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMovie_no() {
		return movie_no;
	}

	public void setMovie_no(int movie_no) {
		this.movie_no = movie_no;
	}

	@Override
	public String toString() {
		return "MovieInfoVo [movie_name=" + movie_name + ", screen_num=" + screen_num + ", movie_time=" + movie_time
				+ ", seat_amount=" + seat_amount + ", price=" + price + ", movie_no=" + movie_no + ", movieBooKingVo="
				+ movieBooKingVo + "]";
	}

	

}
