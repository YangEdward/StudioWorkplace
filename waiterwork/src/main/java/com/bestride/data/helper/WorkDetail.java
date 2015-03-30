package com.bestride.data.helper;

public class WorkDetail {
	
	private String datatype;
    private String operate;
	private DespatchWork datalist;

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public DespatchWork getDatalist() {
        return datalist;
    }

    public void setDatalist(DespatchWork datalist) {
        this.datalist = datalist;
    }
}
