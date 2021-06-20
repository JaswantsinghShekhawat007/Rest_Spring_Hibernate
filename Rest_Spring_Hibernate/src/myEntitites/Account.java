package myEntitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
	@Column(name="accno")
	private String accno;
	
	
	@Column(name="accnm")
	private String accnm;
	
	@Column(name="acctype")
	private String acctype;
	
	@Column(name="balance")
	private Double balance;
	
	
	public Account() {
		accno="";
		accnm="";
		acctype="";
        balance=0.0;
	}


	public String getAccno() {
		return accno;
	}


	public void setAccno(String accno) {
		this.accno = accno;
	}


	public String getAccnm() {
		return accnm;
	}


	public void setAccnm(String accnm) {
		this.accnm = accnm;
	}


	public String getAcctype() {
		return acctype;
	}


	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}


	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
