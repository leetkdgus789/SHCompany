package model;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;

public class EmployeeVO {

	private LocalDate joindate;
	private int code;
	private String name;
	private String rrnum;
	private String addr;
	private String addr2;
	private String email;
	private LocalDate resigndate;

	public EmployeeVO() {
	}

	public EmployeeVO(int code, String name, String rrnum) {
		this.code = code;
		this.name = name;
		this.rrnum = rrnum;
	}
	

	public EmployeeVO(LocalDate joindate, int code, String rrnum, String addr, String addr2, String email,
			LocalDate resigndate) {
		this.joindate = joindate;
		this.code = code;
		this.rrnum = rrnum;
		this.addr = addr;
		this.addr2 = addr2;
		this.email = email;
		this.resigndate = resigndate;
	}

	public EmployeeVO(LocalDate joindate, int code, String addr, String addr2, String email) {
		this.joindate = joindate;
		this.code = code;
		this.addr = addr;
		this.addr2 = addr2;
		this.email = email;
	}

	public EmployeeVO(LocalDate joindate, int code, String addr, String addr2, String email, LocalDate resigndate) {
		this.joindate = joindate;
		this.code = code;
		this.addr = addr;
		this.addr2 = addr2;
		this.email = email;
		this.resigndate = resigndate;
	}

	public EmployeeVO(LocalDate joindate, int code, String name, String rrnum, String addr, String addr2, String email,
			LocalDate resigndate) {
		this.joindate = joindate;
		this.code = code;
		this.name = name;
		this.rrnum = rrnum;
		this.addr = addr;
		this.addr2 = addr2;
		this.email = email;
		this.resigndate = resigndate;
	}

	public EmployeeVO(LocalDate joindate, int code, String name, String rrnum, String addr, String addr2,
			String email) {
		this.joindate = joindate;
		this.code = code;
		this.name = name;
		this.rrnum = rrnum;
		this.addr = addr;
		this.addr2 = addr2;
		this.email = email;
	}

	public LocalDate getJoindate() {
		return joindate;
	}

	public void setJoindate(LocalDate joindate) {
		this.joindate = joindate;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRrnum() {
		return rrnum;
	}

	public void setRrnum(String rrnum) {
		this.rrnum = rrnum;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getResigndate() {
		return resigndate;
	}

	public void setResigndate(LocalDate resigndate) {
		this.resigndate = resigndate;
	}

}
