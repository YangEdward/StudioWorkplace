package com.bestride.data.helper;

public class ReportDetail {
	private String tradenote;
	private double amount;
    private String posid;
    private String billid;
	
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

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }
}
