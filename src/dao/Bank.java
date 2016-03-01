package dao;

import java.sql.Date;

import dao.BankStatus;

public class Bank {

	
	private String name;
	private String shortName;
	private String code;
	private String mfo;
	private Date date;
	private String adress;
	private String license;
	private Date licensedate;
	private BankStatus status;

	
	
	
	private Bank() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMfo() {
		return mfo;
	}

	public void setMfo(String mfo) {
		this.mfo = mfo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Date getLicensedate() {
		return licensedate;
	}

	public void setLicensedate(Date licensedate) {
		this.licensedate = licensedate;
	}

	public String getStatus() {
		return status.name();
	}

	public void setStatus(BankStatus status) {
		this.status = status;
	}

	
	
	@Override
	public String toString() {
		return "Bank [name=" + name + ", shortName=" + shortName + ", code=" + code + ", mfo=" + mfo + ", date=" + date
				+ ", adress=" + adress + ", license=" + license + ", licensedate=" + licensedate + ", status=" + status
				+ "]";
	}

	public static Bank createBank(){
		return new Bank();
	}

}
