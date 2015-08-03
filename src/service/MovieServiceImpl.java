package service;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.MovieDao;
import dao.MovieDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import vo.MovieBooKingVo;
import vo.MovieInfoVo;

public class MovieServiceImpl implements MovieService {

	@Override
	public List<MovieInfoVo> moviePlayList(String movieName) throws Exception {
		// 데이터베이스에 접속 (대략 설명으로 하자면 cmd창으로 sql 로그인 까지
		// 해준상태)
		Connection con = getConnection();

		// movieDaoImpl의 객체를 생성한다.
		MovieDao movieDao = MovieDaoImpl.getInstance();

		// movieDaoImpl 클래스에게 데이터베이스 접속 객체를 넘겨준다.
		movieDao.setConnection(con);

		// 메서드 자료형 타입이 UserVo 클래스이기 때문에 리턴도 userVo의 객체
		// 리턴한다.
		// userDaoimpl의 로그인 selectLoginUser 메서드를 호출한다.
		// 사용자가 입력한 값은 userVo에 담겨 있기 때문에
		// userVo를 넘겨준다. 쿼리가 성공하면 userVo에 사용자 정보값이
		// 새로 들어가져 있을 것이며 실패하였다면 userVo는 null이 된다.
		List<MovieInfoVo> movieList = null;
		movieList = movieDao.moviePlayList(movieName);

		// 데이터베이스를 종료한다. select의 경우 데이터 변동이 없었기 때문에
		// 트랜잭션 처리는 안한다.
		close(con);

		// userVo 정보를 리턴한다.
		return movieList;
	}

	@Override
	public List<MovieBooKingVo> selectSeatNum(int movieNo) throws Exception {
		// 데이터베이스에 접속 (대략 설명으로 하자면 cmd창으로 sql 로그인 까지
		// 해준상태)
		Connection con = getConnection();

		// movieDaoImpl의 객체를 생성한다.
		MovieDao movieDao = MovieDaoImpl.getInstance();

		// movieDaoImpl 클래스에게 데이터베이스 접속 객체를 넘겨준다.
		movieDao.setConnection(con);

		// 메서드 자료형 타입이 UserVo 클래스이기 때문에 리턴도 userVo의 객체
		// 리턴한다.
		// userDaoimpl의 로그인 selectLoginUser 메서드를 호출한다.
		// 사용자가 입력한 값은 userVo에 담겨 있기 때문에
		// userVo를 넘겨준다. 쿼리가 성공하면 userVo에 사용자 정보값이
		// 새로 들어가져 있을 것이며 실패하였다면 userVo는 null이 된다.
		List<MovieBooKingVo> booKingList = null;
		booKingList = movieDao.selectSeatMun(movieNo);

		// 데이터베이스를 종료한다. select의 경우 데이터 변동이 없었기 때문에
		// 트랜잭션 처리는 안한다.
		close(con);

		// userVo 정보를 리턴한다.
		return booKingList;
	}

	@Override
	public MovieInfoVo movieInfo(int movieNo) throws Exception {
		// 데이터베이스에 접속 (대략 설명으로 하자면 cmd창으로 sql 로그인 까지
		// 해준상태)
		Connection con = getConnection();

		// UserDaoImpl의 객체를 생성한다.
		MovieDao movieDao = MovieDaoImpl.getInstance();

		// UserDaoImpl 클래스에게 데이터베이스 접속 객체를 넘겨준다.
		movieDao.setConnection(con);

		// 메서드 자료형 타입이 UserVo 클래스이기 때문에 리턴도 userVo의 객체
		// 리턴한다.
		// userDaoimpl의 로그인 selectLoginUser 메서드를 호출한다.
		// 사용자가 입력한 값은 userVo에 담겨 있기 때문에
		// userVo를 넘겨준다. 쿼리가 성공하면 userVo에 사용자 정보값이
		// 새로 들어가져 있을 것이며 실패하였다면 userVo는 null이 된다.
		MovieInfoVo movieInfoVo = null;
		movieInfoVo = movieDao.movieInfo(movieNo);

		// 데이터베이스를 종료한다. select의 경우 데이터 변동이 없었기 때문에
		// 트랜잭션 처리는 안한다.
		close(con);

		// userVo 정보를 리턴한다.
		return movieInfoVo;
	}

	@Override
	public int moviePay(int movieNo, String[] sList, String userId) throws Exception {
		// TODO Auto-generated method stub
		// 데이터베이스에 접속 (대략 설명으로 하자면 cmd창으로 sql 로그인 까지
		// 해준상태)
		Connection con = getConnection();

		// UserDaoImpl의 객체를 생성한다.
		MovieDao movieDao = MovieDaoImpl.getInstance();

		// UserDaoImpl 클래스에게 데이터베이스 접속 객체를 넘겨준다.
		movieDao.setConnection(con);

		// Insert 레코드가 들어갔는지 확인하기 위한 변수
		// 0은 insert 실패
		// 1은 성공
		int insertCount = 0;

		for (int i = 0; i < sList.length; i++) {
			insertCount = movieDao.moviePay(movieNo, sList[i], userId);
		}

		if (insertCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		// 데이터베이스를 종료한다.
		close(con);

		// 쿼리 결과를 boolean 타입으로 리턴
		return insertCount;
	}

	@Override
	public List<MovieInfoVo> myBooKingList(String userId) throws Exception {
		// 데이터베이스에 접속 (대략 설명으로 하자면 cmd창으로 sql 로그인 까지
		// 해준상태)
		Connection con = getConnection();

		// movieDaoImpl의 객체를 생성한다.
		MovieDao movieDao = MovieDaoImpl.getInstance();

		// movieDaoImpl 클래스에게 데이터베이스 접속 객체를 넘겨준다.
		movieDao.setConnection(con);

		// 메서드 자료형 타입이 UserVo 클래스이기 때문에 리턴도 userVo의 객체
		// 리턴한다.
		// userDaoimpl의 로그인 selectLoginUser 메서드를 호출한다.
		// 사용자가 입력한 값은 userVo에 담겨 있기 때문에
		// userVo를 넘겨준다. 쿼리가 성공하면 userVo에 사용자 정보값이
		// 새로 들어가져 있을 것이며 실패하였다면 userVo는 null이 된다.
		List<MovieInfoVo> myBooKingList = null;
		myBooKingList = movieDao.myBooKingList(userId);

		// 데이터베이스를 종료한다. select의 경우 데이터 변동이 없었기 때문에
		// 트랜잭션 처리는 안한다.
		close(con);

		// userVo 정보를 리턴한다.
		return myBooKingList;
	}

	@Override
	public int movieCancel(int movieNo, int seatNum) throws Exception {
		// 데이터베이스에 접속 (대략 설명으로 하자면 cmd창으로 sql 로그인 까지
		// 해준상태)
		Connection con = getConnection();

		// UserDaoImpl의 객체를 생성한다.
		MovieDao movieDao = MovieDaoImpl.getInstance();

		// UserDaoImpl 클래스에게 데이터베이스 접속 객체를 넘겨준다.
		movieDao.setConnection(con);

		// Insert 레코드가 들어갔는지 확인하기 위한 변수
		// 0은 insert 실패
		// 1은 성공
		int deleteCount = 0;

		deleteCount = movieDao.movieCancel(movieNo, seatNum);
	

		if (deleteCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		// 데이터베이스를 종료한다.
		close(con);

		// 쿼리 결과를 boolean 타입으로 리턴
		return deleteCount;
	}

}
