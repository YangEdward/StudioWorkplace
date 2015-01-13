package com.bestride.data.helper;

import com.bestride.helper.FinalValue;

import android.os.Parcel;
import android.os.Parcelable;

public class Room implements Parcelable,GridHeaderData {

    private String typeid = "5";

    private String roomid = "1235";

    private String roomStateid = "1";

    private int roomstate = FinalValue.USED;

    private int cleanstate = 1;

    private String roomno = "1006";

    private String floorarea = "2654";

    private int floor = 10;

    private String typecode = "CS";

    public static final Parcelable.Creator<Room> CREATOR = new Creator(){

        @Override
        public Room createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            Room room = new Room();
            room.setTypeid(source.readString());
            room.setRoomid(source.readString());
            room.setRoomStateid(source.readString());
            room.setRoomstate(source.readInt());
            room.setCleanstate(source.readInt());
            room.setRoomno(source.readString());
            room.setFloorarea(source.readString());
            room.setFloor(source.readInt());
            room.setTypecode(source.readString());
            return room;
        }

        @Override
        public Room[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Room[size];
        }
    };

    public Room() {
		super();
	}

	
	public String getTypeid() {
		return typeid;
	}


	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}


	public String getRoomid() {
		return roomid;
	}


	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}


	public String getRoomStateid() {
		return roomStateid;
	}


	public void setRoomStateid(String roomStateid) {
		this.roomStateid = roomStateid;
	}


	public int getRoomstate() {
		return roomstate;
	}


	public void setRoomstate(int roomstate) {
		this.roomstate = roomstate;
	}


	public int getCleanstate() {
		return cleanstate;
	}


	public void setCleanstate(int cleanstate) {
		this.cleanstate = cleanstate;
	}


	public String getRoomno() {
		return roomno;
	}


	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}


	public String getFloorarea() {
		return floorarea;
	}


	public void setFloorarea(String floorarea) {
		this.floorarea = floorarea;
	}


	public int getFloor() {
		return floor;
	}


	public void setFloor(int floor) {
		this.floor = floor;
	}


	public String getTypecode() {
		return typecode;
	}


	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
    
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(typeid);
		dest.writeString(roomid);
		dest.writeString(roomStateid);
		dest.writeInt(roomstate);
		dest.writeInt(cleanstate);
		dest.writeString(roomno);
		dest.writeString(floorarea);
		dest.writeInt(floor);
		dest.writeString(typecode);
	}


	@Override
	public int getHeader() {
		// TODO Auto-generated method stub
		return floor;
	}


	@Override
	public String getHeaderString() {
		// TODO Auto-generated method stub
		return floor + FinalValue.FLOOR_ENGLISH;
	}

}
