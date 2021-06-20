package myEntitites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transactions {
	@Id
	@Column(name="fromacno")
	private String fromacno;
	@Column(name="toacno")
	private String toacno;
	@Column(name="amount")
	private double amount;
	@Column(name="transdt")
	private Date transdt;
	
	public Transactions() {
		fromacno="";
		toacno="";
		amount=0.0;
		transdt=new Date();
	}

	
	
	public String getFromacno() {
		return fromacno;
	}

	public void setFromacno(String fromacno) {
		this.fromacno = fromacno;
	}

	public String getToacno() {
		return toacno;
	}

	public void setToacno(String toacno) {
		this.toacno = toacno;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransdt() {
		return transdt;
	}

	public void setTransdt(Date transdt) {
		this.transdt = transdt;
	}




}
