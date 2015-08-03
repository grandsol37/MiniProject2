package dao;

import static util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MovieBooKingVo;
import vo.MovieInfoVo;

public class MovieDaoImpl implements MovieDao {

	// 커넥션 객체를 이 클래스외 다른 클래스에서 사용하지 못하게 은닉
	private Connection con;

	// 간접적으로 호출된 기본 생성자
	private static MovieDaoImpl instance = new MovieDaoImpl();

	// 간접적으로 생성자를 호출 한다.
	public static MovieDaoImpl getInstance() {
		return instance;
	}

	// UserDaoImpl을 생성자를 다른 클래스에서 직접 호출하지 못하게 하기 위해
	private MovieDaoImpl() {
	}

	// 커넥션 정보를 멤버필드에 담는다.
	@Override
	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}

	@Override
	public List<MovieInfoVo> moviePlayList(String movieName) throws Exception {
		// SELECT한 결과 값을 담기 위한 객체
		List<MovieInfoVo> movieList = null;

		// 쿼리를 DB에 사용하기 위한 객체
		PreparedStatement pstmt = null;

		// 쿼리를 수행하여 SELECT의 컬럼 값들은 ResultSet 객체에 담긴다.
		// ResultSet 객체
		ResultSet rs = null;

		// SELECT의 쿼리 문
		String sql = "SELECT movie_name, screen_num, movie_time, " + " seat_amount, price, movie_no FROM MOVIE_INFO "
				+ " WHERE movie_name = ? " + " AND movie_time >= SYSDATE-1/24 " + " AND movie_time <= SYSDATE+10/24"
				+ " ORDER BY movie_time ASC";

		try {
			// 쿼리문을 DB에 전송했다고 보면됨
			// (cmd창에 쿼리를 친 것과 같다고 보면됨)
			pstmt = con.prepareStatement(sql);

			// 쿼리문을 보면 ?, ? 가 있는데 이것은 바인딩 처리라고 하며
			// 아래를 보면 쿼리에 ?를 대입한다. (?의순서, ?의내용)
			// System.out.println(movieName);
			pstmt.setString(1, movieName);

			// 쿼리의 조회 결과 컬럼 값들은 ResultSet 객체에 담겨진다.
			rs = pstmt.executeQuery();

			// ResultSet의 담겨진 컬럼 값들을 뽑아내서 UserVO 객체에 값을
			// 담는다.
			if (rs.next()) {
				movieList = new ArrayList<MovieInfoVo>();
				do {
					// UserVo의 객체 이름은 data이며 기본생성자로 초기화 한 후
					// Get 메서드를 이용하여 값을 담는다.
					MovieInfoVo movieInfoVo = new MovieInfoVo();
					movieInfoVo.setMovie_name(rs.getString("movie_name"));
					movieInfoVo.setScreen_num(rs.getString("screen_num"));
					movieInfoVo.setMovie_time(rs.getTimestamp("movie_time"));
					movieInfoVo.setSeat_amount(rs.getInt("seat_amount"));
					movieInfoVo.setPrice(rs.getInt("price"));
					movieInfoVo.setMovie_no(rs.getInt("movie_no"));

					movieList.add(movieInfoVo);

				} while (rs.next());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// ResultSet을 종료한다.
			close(rs);
			// PreparedStatement를 종료한다.
			close(pstmt);
		}

		// UserVO의 객체를 리턴한다.
		return movieList;
	}

	@Override
	public List<MovieBooKingVo> selectSeatMun(int movieNo) throws Exception {
		// SELECT한 결과 값을 담기 위한 객체
		List<MovieBooKingVo> bookingList = null;

		// 쿼리를 DB에 사용하기 위한 객체
		PreparedStatement pstmt = null;

		// 쿼리를 수행하여 SELECT의 컬럼 값들은 ResultSet 객체에 담긴다.
		// ResultSet 객체
		ResultSet rs = null;

		// SELECT의 쿼리 문
		String sql = " SELECT b.res_seat_num " + " FROM movie_info i, movie_booking b "
				+ " WHERE i.movie_no = b.movie_no " + " AND i.movie_no = ?";

		try {
			// 쿼리문을 DB에 전송했다고 보면됨
			// (cmd창에 쿼리를 친 것과 같다고 보면됨)
			pstmt = con.prepareStatement(sql);

			// 쿼리문을 보면 ?, ? 가 있는데 이것은 바인딩 처리라고 하며
			// 아래를 보면 쿼리에 ?를 대입한다. (?의순서, ?의내용)
			pstmt.setInt(1, movieNo);

			// 쿼리의 조회 결과 컬럼 값들은 ResultSet 객체에 담겨진다.
			rs = pstmt.executeQuery();

			// ResultSet의 담겨진 컬럼 값들을 뽑아내서 UserVO 객체에 값을
			// 담는다.
			if (rs.next()) {
				bookingList = new ArrayList<MovieBooKingVo>();
				do {
					// UserVo의 객체 이름은 data이며 기본생성자로 초기화 한 후
					// Get 메서드를 이용하여 값을 담는다.
					MovieBooKingVo movieBooKingVo = new MovieBooKingVo();
					movieBooKingVo.setRes_seat_num(rs.getInt("res_seat_num"));

					bookingList.add(movieBooKingVo);

				} while (rs.next());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("조회된 결과 없슴");
		} finally {
			// ResultSet을 종료한다.
			close(rs);
			// PreparedStatement를 종료한다.
			close(pstmt);
		}

		// UserVO의 객체를 리턴한다.
		return bookingList;
	}

	@Override
	public MovieInfoVo movieInfo(int movieNo) throws Exception {
		// 쿼리를 DB에 사용하기 위한 객체
		PreparedStatement pstmt = null;

		MovieInfoVo movieInfoVo = null;

		// 쿼리를 수행하여 SELECT의 컬럼 값들은 ResultSet 객체에 담긴다.
		// ResultSet 객체
		ResultSet rs = null;

		// 결과값을 담기 위한 변수 초기값을 -1로 줌
		int count = -1;

		// 쿼리문
		String sql = "SELECT screen_num, price, movie_time FROM movie_info WHERE movie_no = ? ";

		try {
			// 쿼리문을 DB에 전송했다고 보면됨
			// (cmd창에 쿼리를 친 것과 같다고 보면됨)
			pstmt = con.prepareStatement(sql);

			// 쿼리문을 보면 ? 가 있는데 이것은 바인딩 처리라고 하며
			// 아래를 보면 쿼리에 ?를 대입한다. (?의순서, ?의내용)
			pstmt.setInt(1, movieNo);

			// 쿼리의 조회 결과 컬럼 값들은 ResultSet 객체에 담겨진다.
			rs = pstmt.executeQuery();

			// ResultSet의 담겨진 컬럼 값을(COUNT) 뽑아내서
			// count에 담는다.
			if (rs.next()) {
				movieInfoVo = new MovieInfoVo();
				movieInfoVo.setScreen_num(rs.getString("screen_num"));
				movieInfoVo.setPrice(rs.getInt("price"));
				movieInfoVo.setMovie_time(rs.getTimestamp("movie_time"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// ResultSet을 종료한다.
			close(rs);
			// PreparedStatement를 종료한다.
			close(pstmt);
		}

		// id 중복 갯수를 리턴한다.
		return movieInfoVo;
	}

	@Override
	public int moviePay(int movieNo, String sList, String userId) throws Exception {

		PreparedStatement pstmt = null;

		int insertCount = 0;
		int updateCount = 0;

		try {

			pstmt = con
					.prepareStatement("INSERT INTO MOVIE_BOOKING (res_seat_num, user_id, movie_no) VALUES (?, ?, ?)");
			pstmt.setString(1, sList);
			pstmt.setString(2, userId);
			pstmt.setInt(3, movieNo);
			insertCount = pstmt.executeUpdate();

			pstmt = con.prepareStatement("UPDATE MOVIE_INFO SET seat_amount=seat_amount-1 WHERE movie_no = ?");
			pstmt.setInt(1, movieNo);
			updateCount = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount + updateCount;
	}

	@Override
	public List<MovieInfoVo> myBooKingList(String userId) throws Exception {
		// SELECT한 결과 값을 담기 위한 객체
		List<MovieInfoVo> myBooKingList = null;

		// 쿼리를 DB에 사용하기 위한 객체
		PreparedStatement pstmt = null;

		// 쿼리를 수행하여 SELECT의 컬럼 값들은 ResultSet 객체에 담긴다.
		// ResultSet 객체
		ResultSet rs = null;

		// SELECT의 쿼리 문
		String sql = " SELECT i.movie_name, i.screen_num, "
				+ " i.movie_time, i.price, b.movie_no, "
				+ " b.res_seat_num FROM movie_info i, movie_booking b "
				+ " WHERE i.movie_no = b.movie_no "
				+ " AND i.movie_time > SYSDATE AND b.user_id = ? ";

		try {
			// 쿼리문을 DB에 전송했다고 보면됨
			// (cmd창에 쿼리를 친 것과 같다고 보면됨)
			pstmt = con.prepareStatement(sql);

			// 쿼리문을 보면 ?, ? 가 있는데 이것은 바인딩 처리라고 하며
			// 아래를 보면 쿼리에 ?를 대입한다. (?의순서, ?의내용)
			pstmt.setString(1, userId);

			// 쿼리의 조회 결과 컬럼 값들은 ResultSet 객체에 담겨진다.
			rs = pstmt.executeQuery();

			// ResultSet의 담겨진 컬럼 값들을 뽑아내서 UserVO 객체에 값을
			// 담는다.
			if (rs.next()) {
				myBooKingList = new ArrayList<MovieInfoVo>();
				do {
					// UserVo의 객체 이름은 data이며 기본생성자로 초기화 한 후
					// Get 메서드를 이용하여 값을 담는다.
					MovieInfoVo movieInfoVo = new MovieInfoVo();
					MovieBooKingVo movieBooKingVo = new MovieBooKingVo();
					movieInfoVo.setMovieBooKingVo(movieBooKingVo);

					movieInfoVo.setMovie_name(rs.getString("movie_name"));
					movieInfoVo.setScreen_num(rs.getString("screen_num"));
					movieInfoVo.setMovie_time(rs.getTimestamp("movie_time"));
					movieInfoVo.setPrice(rs.getInt("price"));
					movieInfoVo.setMovie_no(rs.getInt("movie_no"));
					movieBooKingVo.setRes_seat_num(rs.getInt("res_seat_num"));

					myBooKingList.add(movieInfoVo);

				} while (rs.next());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("조회된 결과 없슴");
		} finally {
			// ResultSet을 종료한다.
			close(rs);
			// PreparedStatement를 종료한다.
			close(pstmt);
		}

		// UserVO의 객체를 리턴한다.
		return myBooKingList;
	}

	@Override
	public int movieCancel(int movieNo, int seatNum) throws Exception {

		PreparedStatement pstmt = null;

		int deleteCount = 0;
		int updateCount = 0;

		try {

			String id = "admin";
			pstmt = con.prepareStatement("DELETE FROM MOVIE_BOOKING "
					+ " WHERE movie_no = ? AND res_seat_num = ?");
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, seatNum);
			deleteCount = pstmt.executeUpdate();

			pstmt = con.prepareStatement("UPDATE MOVIE_INFO SET seat_amount=seat_amount+1 WHERE movie_no = ?");
			pstmt.setInt(1, movieNo);
			updateCount = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount + updateCount;
	}

}
