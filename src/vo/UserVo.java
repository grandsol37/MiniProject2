package vo;

import java.sql.Timestamp;
import java.util.Date;

public class UserVo {
	
	private String user_id;		
	private String user_name;
	private String user_pwd;
	private String user_pwd_hint;
	private String user_pwd_answer;
	private int user_gender;
	private String user_email;
	private String user_phone;
	private String user_zipcode;
	private String user_addr1;		
	private String user_addr2;		
	private Timestamp user_regdate;
	private String user_lastip;		
	private int user_level;		
	private int user_status;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_pwd_hint() {
		return user_pwd_hint;
	}
	public void setUser_pwd_hint(String user_pwd_hint) {
		this.user_pwd_hint = user_pwd_hint;
	}
	public String getUser_pwd_answer() {
		return user_pwd_answer;
	}
	public void setUser_pwd_answer(String user_pwd_answer) {
		this.user_pwd_answer = user_pwd_answer;
	}
	public int getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(int user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_zipcode() {
		return user_zipcode;
	}
	public void setUser_zipcode(String user_zipcode) {
		this.user_zipcode = user_zipcode;
	}
	public String getUser_addr1() {
		return user_addr1;
	}
	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}
	public String getUser_addr2() {
		return user_addr2;
	}
	public void setUser_addr2(String user_addr2) {
		this.user_addr2 = user_addr2;
	}
	public Timestamp getUser_regdate() {
		return user_regdate;
	}
	public void setUser_regdate(Timestamp user_regdate) {
		this.user_regdate = user_regdate;
	}
	public String getUser_lastip() {
		return user_lastip;
	}
	public void setUser_lastip(String user_lastip) {
		this.user_lastip = user_lastip;
	}
	public int getUser_level() {
		return user_level;
	}
	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	
	@Override
	public String toString() {
		return "UserVo [user_id=" + user_id + ", user_name=" + user_name + ", user_pwd=" + user_pwd + ", user_pwd_hint="
				+ user_pwd_hint + ", user_pwd_answer=" + user_pwd_answer + ", user_gender=" + user_gender
				+ ", user_email=" + user_email + ", user_phone=" + user_phone + ", user_zipcode=" + user_zipcode
				+ ", user_addr1=" + user_addr1 + ", user_addr2=" + user_addr2 + ", user_regdate=" + user_regdate
				+ ", user_lastip=" + user_lastip + ", user_level=" + user_level + ", user_status=" + user_status + "]";
	}
}
