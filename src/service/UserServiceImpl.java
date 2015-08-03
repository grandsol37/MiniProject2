package service;

import static util.JdbcUtil.close;
import static util.JdbcUtil.commit;
import static util.JdbcUtil.getConnection;
import static util.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDao;
import dao.UserDaoImpl;
import vo.UserVo;

public class UserServiceImpl implements UserService {

	@Override
	public boolean joinUser(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		// 데이터베이스에 접속 (대략 설명으로 하자면 cmd창으로 sql 로그인 까지
		// 해준상태)
		Connection con = getConnection();
		
		//UserDaoImpl의 객체를 생성한다.
		UserDao userDao = UserDaoImpl.getInstance();
		
		// UserDaoImpl 클래스에게 데이터베이스 접속 객체를 넘겨준다.
		userDao.setConnection(con);
		
		// 메서드 자료형 타입이 boolean이기 때문에 리턴도 boolean여서
		// isRegistSuccess를 리턴하기 위해 초기 선언
		boolean isRegistSuccess = false;
		
		
		// Insert 레코드가 들어갔는지 확인하기 위한 변수
		// 0은 insert 실패
		// 1은 성공
		int insertCount = 0;
		
		// userDaoimpl의 가입 Insert 메서드를 호출한다.
		// 사용자가 입력한 값은 userVo에 담겨 있기 때문에
		// userVo를 넘겨준다. 쿼리가 성공하면 1 실패하면 0
		insertCount = userDao.joinUser(userVo);
		
		// 0보다 클경우 1이상이기 때문에 쿼리가 정상적으로 수행된 것이다.
		if (insertCount > 0) {
			// boolean타입을 리턴하기 위해 성공하였다는 의미로 true를 리턴
			isRegistSuccess = true;
			
			// 쿼리가 정상적으로 성공 한 것이기 때문에 트랜잭션 커밋을 한다.
			commit(con);
		} else {
			// 쿼리 수행이 실패 한 것이므로 롤백을 한다.
			rollback(con);
		}
		
		//데이터베이스를 종료한다.
		close(con);
		
		//쿼리 결과를 boolean 타입으로 리턴
		return isRegistSuccess;
	}

	@Override
	public UserVo selectLoingUser(UserVo userVo) throws Exception {
		// 데이터베이스에 접속 (대략 설명으로 하자면 cmd창으로 sql 로그인 까지
		// 해준상태)
		Connection con = getConnection();
		
		//UserDaoImpl의 객체를 생성한다.
		UserDao userDao = UserDaoImpl.getInstance();
		
		// UserDaoImpl 클래스에게 데이터베이스 접속 객체를 넘겨준다.
		userDao.setConnection(con);
		
		// 메서드 자료형 타입이 UserVo 클래스이기 때문에 리턴도 userVo의 객체
		// 리턴한다.
		// userDaoimpl의 로그인 selectLoginUser 메서드를 호출한다.
		// 사용자가 입력한 값은 userVo에 담겨 있기 때문에
		// userVo를 넘겨준다. 쿼리가 성공하면 userVo에 사용자 정보값이
		// 새로 들어가져 있을 것이며 실패하였다면 userVo는 null이 된다.
		userVo = userDao.selectLoginUser(userVo);
		
		//데이터베이스를 종료한다. select의 경우 데이터 변동이 없었기 때문에
		//트랜잭션 처리는 안한다.
		close(con);
		
		// userVo 정보를 리턴한다.
		return userVo;
	}


	@Override
	public int idSelect(String id) throws Exception {
		// 데이터베이스에 접속 (대략 설명으로 하자면 cmd창으로 sql 로그인 까지
		// 해준상태)
		Connection con = getConnection();
		
		//UserDaoImpl의 객체를 생성한다.
		UserDao userDao = UserDaoImpl.getInstance();
		
		// UserDaoImpl 클래스에게 데이터베이스 접속 객체를 넘겨준다.
		userDao.setConnection(con);
		
		// 중복아이디 카운트 수를 받기 위해 -1값으로 초기화
		int idCount = -1;
		
		// 메서드 자료형 타입이 int이기 때문에 리턴도 int의 변수
		// idCount를 리턴한다.
		// userDaoimpl의 아이디 중복검사 idSelect 메서드를 호출한다.
		// 사용자가 입력한 값은 String id에 담겨 있기 때문에
		// id를 넘겨준다. 쿼리가 성공하면 idCount에 값이 들어간다.
		// 0이라면 중복값이 없는 것이며 1이상이라면 중복되는 값이 있다는 것이다.
		idCount = userDao.idSelect(id);
		
		//데이터베이스를 종료한다.
		close(con);
		
		// 중복 아이디가 있는지 없는지 갯수를 리턴한다.
		return idCount;
	}

	
}
