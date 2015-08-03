package dao;

import java.sql.Connection;
import java.util.List;

import vo.MovieBooKingVo;
import vo.MovieInfoVo;

public interface MovieDao {
	
	public void setConnection(Connection con);
	
	/**
     * 선택한 영화의 상영관, 상영시간, 인원수 리스트 등
     * DB에서 정보를 가져와 리스트에 담는다.
     * @return List
     */
	public List<MovieInfoVo> moviePlayList(String movieName) throws Exception;
	
	/**
     * 영화를 볼 스크린 자리중 예약된 자리를 구하는 메서드
     * DB에서 정보를 가져와 리스트에 담는다.
     * @return List
     */
	public List<MovieBooKingVo> selectSeatMun(int movieNo) throws Exception;
	
	/**
     * 간략한 영화표를 출력하기 위해 필요한 정보를 가져오는 메서드
     * DB에서 정보를 가져와 MovieInfoVo에 담는다.
     * @return MovieInfoVo
     */
	public MovieInfoVo movieInfo(int movieNo) throws Exception;
	
	/**
     * 영화표를 구매할 때 예약테이블과 영화정보 테이블을 수정한다.
     * 예약 테이블은 정보 추가이므로 INSERT
     * 영화정보 테이블은 상영관의 남은 자릿수를 수정하기 위한 UPDATE
     * @return 0 or 1
     */
	public int moviePay(int movieNo, String sList, String userId) throws Exception;
	
	/**
     * 예매 취소를 클릭한다면 본인의 예약 리스트를 보여주는 메서드
     * 예매취소를 누른 시간보다 늦게 상영하는 예약영화 목록을 보여준다.
     * @return List
     */
	public List<MovieInfoVo> myBooKingList(String userId) throws Exception;
	
	
	/**
     * 예매 취소를 확정하면 호출되는 메서드
     * 예약 테이블은 정보 삭제이므로 DELETE
     * 영화정보 테이블은 상영관의 남은 자릿수를 수정하기 위한 UPDATE
     * @return 0 or 1
     */
	public int movieCancel(int movieNo, int seatNum) throws Exception;
	
	

}
