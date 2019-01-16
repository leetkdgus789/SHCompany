package model;

public class SalaryVO {
	private int no;
	private int year;
	private int month;
	private int code;
	private String name;

	private String basepay;
	private String bonus;
	private String overpay;
	private String sumpay;
	private String pension;
	private String health;
	private String empment;
	private String withtax;
	private String localtax;
	private String sumtax;
	private String actualpay;

	private String rrnum;
	private String joindate;
	private String resigndate;
	
	public SalaryVO() {
	}

	
	
	public SalaryVO(int year, int month, int code, String name, String basepay, String bonus, String overpay,
			String sumpay, String pension, String health, String empment, String withtax, String localtax,
			String sumtax, String actualpay) {
		this.year = year;
		this.month = month;
		this.code = code;
		this.name = name;
		this.basepay = basepay;
		this.bonus = bonus;
		this.overpay = overpay;
		this.sumpay = sumpay;
		this.pension = pension;
		this.health = health;
		this.empment = empment;
		this.withtax = withtax;
		this.localtax = localtax;
		this.sumtax = sumtax;
		this.actualpay = actualpay;
	}



	public SalaryVO(int year, int month, int code, String basepay, String bonus, String overpay, String sumpay,
			String pension, String health, String empment, String withtax, String localtax, String sumtax,
			String actualpay) {
		this.year = year;
		this.month = month;
		this.code = code;
		this.basepay = basepay;
		this.bonus = bonus;
		this.overpay = overpay;
		this.sumpay = sumpay;
		this.pension = pension;
		this.health = health;
		this.empment = empment;
		this.withtax = withtax;
		this.localtax = localtax;
		this.sumtax = sumtax;
		this.actualpay = actualpay;
	}



	public SalaryVO(int no, int year, int month, int code, String name, String basepay, String bonus, String overpay,
			String sumpay, String pension, String health, String empment, String withtax, String localtax,
			String sumtax, String actualpay, String rrnum, String joindate, String resigndate) {
		this.no = no;
		this.year = year;
		this.month = month;
		this.code = code;
		this.name = name;
		this.basepay = basepay;
		this.bonus = bonus;
		this.overpay = overpay;
		this.sumpay = sumpay;
		this.pension = pension;
		this.health = health;
		this.empment = empment;
		this.withtax = withtax;
		this.localtax = localtax;
		this.sumtax = sumtax;
		this.actualpay = actualpay;
		this.rrnum = rrnum;
		this.joindate = joindate;
		this.resigndate = resigndate;
	}

	public SalaryVO(int year, int month, int code, String name, String basepay, String bonus, String overpay,
			String sumpay, String pension, String health, String empment, String withtax, String localtax,
			String sumtax, String actualpay, String rrnum, String joindate, String resigndate) {
		this.year = year;
		this.month = month;
		this.code = code;
		this.name = name;
		this.basepay = basepay;
		this.bonus = bonus;
		this.overpay = overpay;
		this.sumpay = sumpay;
		this.pension = pension;
		this.health = health;
		this.empment = empment;
		this.withtax = withtax;
		this.localtax = localtax;
		this.sumtax = sumtax;
		this.actualpay = actualpay;
		this.rrnum = rrnum;
		this.joindate = joindate;
		this.resigndate = resigndate;
	}

	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
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

	public String getBasepay() {
		return basepay;
	}

	public void setBasepay(String basepay) {
		this.basepay = basepay;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getOverpay() {
		return overpay;
	}

	public void setOverpay(String overpay) {
		this.overpay = overpay;
	}

	public String getSumpay() {
		return sumpay;
	}

	public void setSumpay(String sumpay) {
		this.sumpay = sumpay;
	}

	public String getPension() {
		return pension;
	}

	public void setPension(String pension) {
		this.pension = pension;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getEmpment() {
		return empment;
	}

	public void setEmpment(String empment) {
		this.empment = empment;
	}

	public String getWithtax() {
		return withtax;
	}

	public void setWithtax(String withtax) {
		this.withtax = withtax;
	}

	public String getLocaltax() {
		return localtax;
	}

	public void setLocaltax(String localtax) {
		this.localtax = localtax;
	}

	public String getSumtax() {
		return sumtax;
	}

	public void setSumtax(String sumtax) {
		this.sumtax = sumtax;
	}

	public String getActualpay() {
		return actualpay;
	}

	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}

	public String getRrnum() {
		return rrnum;
	}

	public void setRrnum(String rrnum) {
		this.rrnum = rrnum;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public String getResigndate() {
		return resigndate;
	}

	public void setResigndate(String resigndate) {
		this.resigndate = resigndate;
	}

}