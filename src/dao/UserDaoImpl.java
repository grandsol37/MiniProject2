package dao;

import static util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.UserVo;

public class UserDaoImpl implements UserDao {
	// 커넥션 객체를 이 클래스외 다른 클래스에서 사용하지 못하게 은닉 
	private Connection con;
	
	// 간접적으로 호출된 기본 생성자
	private static UserDaoImpl instance = new UserDaoImpl();
	
	// 간접적으로 생성자를 호출 한다.
	public static UserDaoImpl getInstance() {
		return instance;
	}
	
	
	// UserDaoImpl을 생성자를 다른 클래스에서 직접 호출하지 못하게 하기 위해
	private UserDaoImpl() {
	}
	
	//커넥션 정보를 멤버필드에 담는다.
	@Override
	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}

	@Override
	public int joinUser(UserVo userVo) throws Exception {
		// 쿼리를 DB에 사용하기 위한 객체
		PreparedStatement pstmt = null;
		
		//리턴할 변수 - 초기값으로 0
		int id = 0;
		
		// SQL 쿼리문
		String sql = "INSERT INTO tab_user(" 
				+ " user_id, user_name, user_pwd, user_pwd_hint, "
				+ " user_pwd_answer, user_gender, user_email, user_phone, "
				+ " user_zipcode, user_addr1, user_addr2 ) VALUES "
				+ " ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

		try {
			// 쿼리문을 DB에 전송했다고 보면됨 
			// (cmd창에 쿼리를 친 것과 같다고 보면됨)
			pstmt = con.prepareStatement(sql);
			
			// 쿼리문을 보면 ?, ?, ?,가 있는데 이것은 바인딩 처리라고 하며
			// 아래를 보면 쿼리에 ?를 대입한다. (?의순서, ?의내용)
			int k = 1; // 순서를 1 2 3 으로 쓰기 귀찮아서 증감연산자로쓰기위해

			pstmt.setString(k++, userVo.getUser_id());
			pstmt.setString(k++, userVo.getUser_name());
			pstmt.setString(k++, userVo.getUser_pwd());
			pstmt.setString(k++, userVo.getUser_pwd_hint());
			pstmt.setString(k++, userVo.getUser_pwd_answer());
			pstmt.setInt(k++, userVo.getUser_gender());
			pstmt.setString(k++, userVo.getUser_email());
			pstmt.setString(k++, userVo.getUser_phone());
			pstmt.setString(k++, userVo.getUser_zipcode());
			pstmt.setString(k++, userVo.getUser_addr1());
			pstmt.setString(k++, userVo.getUser_addr2());
			
			//쿼리의 결과를 id 변수에 담는다.
			//SELECT를 제외한 나머지 INSERT, DELETE, UPDATE는
			//몇건이 성공했는지만 리턴해주기 때문에 int로 담아야한다.
			id = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//예외 발생시 결과값은 0
			id = 0;
		} finally {
			//PreparedStatement를 종료한다.
			close(pstmt);
		}
		// Insert의 성공 건수를 리턴한다.
		return id;
	}

	@Override
	public UserVo selectLoginUser(UserVo userVo) throws Exception {
		// SELECT한 결과 값을 담기 위한 객체
		UserVo data = null;
		
		// 쿼리를 DB에 사용하기 위한 객체
		PreparedStatement pstmt = null;
		
		// 쿼리를 수행하여 SELECT의 컬럼 값들은 ResultSet 객체에 담긴다.
		// ResultSet 객체
		ResultSet rs = null;
		
		// SELECT의 쿼리 문
		String sql = "SELECT user_id, user_name, user_pwd_hint, user_pwd_answer, "
				+ " user_gender, user_email, user_phone, user_zipcode, user_addr1, "
				+ " user_addr2, user_regdate, user_lastip, user_level, user_status "
				+ " FROM tab_user WHERE user_id = ? and user_pwd = ?";

		try {
			// 쿼리문을 DB에 전송했다고 보면됨 
			// (cmd창에 쿼리를 친 것과 같다고 보면됨)
			pstmt = con.prepareStatement(sql);
			
			// 쿼리문을 보면 ?, ? 가 있는데 이것은 바인딩 처리라고 하며
			// 아래를 보면 쿼리에 ?를 대입한다. (?의순서, ?의내용)
			pstmt.setString(1, userVo.getUser_id());
			pstmt.setString(2, userVo.getUser_pwd());
			
			//쿼리의 조회 결과 컬럼 값들은 ResultSet 객체에 담겨진다.
			rs = pstmt.executeQuery();
			
			// ResultSet의 담겨진 컬럼 값들을 뽑아내서 UserVO 객체에 값을
			// 담는다.
			if (rs.next()) {
				// UserVo의 객체 이름은 data이며 기본생성자로 초기화 한 후
				// Get 메서드를 이용하여 값을 담는다.
				data = new UserVo();
				data.setUser_id(rs.getString("user_id"));
				data.setUser_name(rs.getString("user_name"));
				data.setUser_pwd_hint(rs.getString("user_pwd_hint"));
				data.setUser_pwd_answer(rs.getString("user_pwd_answer"));
				data.setUser_gender(rs.getInt("user_gender"));
				data.setUser_email(rs.getString("user_email"));
				data.setUser_phone(rs.getString("user_phone"));
				data.setUser_zipcode(rs.getString("user_zipcode"));
				data.setUser_addr1(rs.getString("user_addr1"));
				data.setUser_addr2(rs.getString("user_addr2"));
				data.setUser_regdate(rs.getTimestamp("user_regdate"));
				data.setUser_lastip(rs.getString("user_lastip"));
				data.setUser_level(rs.getInt("user_level"));
				data.setUser_status(rs.getInt("user_status"));
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
		return data;
	}


	@Override
	public int idSelect(String id) throws Exception {
		
		// 쿼리를 DB에 사용하기 위한 객체
		PreparedStatement pstmt = null;
		
		// 쿼리를 수행하여 SELECT의 컬럼 값들은 ResultSet 객체에 담긴다.
		// ResultSet 객체
		ResultSet rs = null;
		
		// 결과값을 담기 위한 변수 초기값을 -1로 줌
		int count = -1;
		
		// 쿼리문
		String sql = "SELECT COUNT(user_id) FROM tab_user WHERE user_id = ? ";

		try {
			// 쿼리문을 DB에 전송했다고 보면됨 
			// (cmd창에 쿼리를 친 것과 같다고 보면됨)
			pstmt = con.prepareStatement(sql);
			
			// 쿼리문을 보면 ? 가 있는데 이것은 바인딩 처리라고 하며
			// 아래를 보면 쿼리에 ?를 대입한다. (?의순서, ?의내용)
			pstmt.setString(1, id);
			
			// 쿼리의 조회 결과 컬럼 값들은 ResultSet 객체에 담겨진다.
			rs = pstmt.executeQuery();
			
			// ResultSet의 담겨진 컬럼 값을(COUNT) 뽑아내서 
			// count에 담는다.
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	

}
