package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName : 파라미터로 지정된 클래스를 메모리로 읽어들이는 역할
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
     * 데이터베이스에 접속한다.
     * @return Connection
     */
	public static Connection getConnection(){
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"java","java");
			//트렌젝션 처리
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	/**
     * 데이터베이스에 대한 접속을 해제한다.
     */
	public static void close(Connection con){
		try {
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
     * 데이터베이스 Statement를 종료한다.
     */
	public static void close(Statement stmt){
		try {
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public static void close(ResultSet rs){
		try {
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
     * 데이터베이스 트랜잭션 처리 commit
     */
	public static void commit(Connection con){
		try {
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
     * 데이터베이스 트랜잭션 처리 rollback
     */
	public static void rollback(Connection con){
		try {
			con.rollback();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
