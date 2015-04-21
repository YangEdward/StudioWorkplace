
package com.bestride.data.helper;

import java.util.List;

public class WorkDetail {

    private String datatype;
    private String operate;
    private List<DespatchWork> datalist;

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

    public List<DespatchWork> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<DespatchWork> datalist) {
        this.datalist = datalist;
    }
}
