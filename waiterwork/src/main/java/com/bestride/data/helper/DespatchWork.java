package com.bestride.data.helper;

/**
 * Created by Edward on 2015/1/30.
 */
public class DespatchWork {
    private String roomid;
    private String roomno;
    private int servtype;
    private String  begintime;
    private String  workid;
    private String  hoteltype;
    private String  note;

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public int getServtype() {
        return servtype;
    }

    public void setServtype(int servtype) {
        this.servtype = servtype;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    public String getHoteltype() {
        return hoteltype;
    }

    public void setHoteltype(String hoteltype) {
        this.hoteltype = hoteltype;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
