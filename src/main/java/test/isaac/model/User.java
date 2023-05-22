package test.isaac.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name="USER", uniqueConstraints= {@UniqueConstraint(columnNames= {"UNO"})})
public class User {
	
	
	public User(Integer uno, String uname, Date ubday, Integer usex, String umail, String upwd, String uacct) {
		super();
		this.uno = uno;
		this.uname = uname;
		this.ubday = ubday;
		this.usex = usex;
		this.umail = umail;
		this.upwd = upwd;
		this.uacct = uacct;
	}



	public User(String uname, Date ubday, Integer usex, String umail, String upwd, String uacct) {
		super();
		this.uname = uname;
		this.ubday = ubday;
		this.usex = usex;
		this.umail = umail;
		this.upwd = upwd;
		this.uacct = uacct;
	}
	
	
	
	public User(){
		super();
	}
	
	@Id
	@Column(name="UNO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer uno;
	
	@Column(name="Uname", length=20)
	private String uname;
	
	@Column(name="UBDAY")
	private Date ubday;
	
	@Column(name="USEX")
	private Integer usex;
	
	@Column(name="UMAIL", length=50)
	private String umail;
	
	@Column(name="UPWD", length=32)
	private String upwd;
	
	@Column(name="UACCT", length=32)
	private String uacct;
	
	
	public Integer getUno() {
		return uno;
	}
	public void setUno(Integer uno) {
		this.uno = uno;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Date getUbday() {
		return ubday;
	}
	public void setUbday(Date ubday) {
		this.ubday = ubday;
	}
	public Integer getUsex() {
		return usex;
	}
	public void setUsex(Integer usex) {
		this.usex = usex;
	}
	public String getUmail() {
		return umail;
	}
	public void setUmail(String umail) {
		this.umail = umail;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUacct() {
		return uacct;
	}
	public void setUacct(String uacct) {
		this.uacct = uacct;
	}
	
}
