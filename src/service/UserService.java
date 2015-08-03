package service;

import vo.UserVo;

public interface UserService {
	
	/**
     * 회원 가입 메서드
     * 회원 가입한 내용을 DB에다 넘겨 INSERT시킴
     * 회원 가입할때 필요한 정보는 UserVo에 담아야 한다.
     * @return boolean
     */
	public boolean joinUser(UserVo userVo) throws Exception;
	
	/**
     * 로그인 메서드
     * 로그인 정보를 DB에다 넘겨 SELECT 시킴
     * 로그인에 필요한 정보는 UserVo에 담아야 한다.
     * 조회가 성공한다면 UserVo에 회원 정보를 담아서 리턴시킨다.
     * @return UserVo
     */
	public UserVo selectLoingUser(UserVo userVo) throws Exception;
	
	/**
     * 아이디 중복 검사 메서드 
     * 입력한 아이디를 DB에다 넘겨 SELECT 시킴
     * 조회가 성공하면 중복 아이디가 없을시 0
     * 중복되는 아이디가 있을 경우 1을 리턴한다.
     * @return 0 or 1
     */
	public int idSelect(String id) throws Exception;

	


}
