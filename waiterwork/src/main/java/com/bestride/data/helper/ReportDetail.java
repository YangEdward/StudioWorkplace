package com.bestride.data.helper;

public class ReportDetail {
	private String tradenote;
	private double amount;
	
	public ReportDetail(String tradenote, double amount) {
		super();
		this.tradenote = tradenote;
		this.amount = amount;
	}
	public String getTradenote() {
		return tradenote;
	}
	public void setTradenote(String tradenote) {
		this.tradenote = tradenote;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
