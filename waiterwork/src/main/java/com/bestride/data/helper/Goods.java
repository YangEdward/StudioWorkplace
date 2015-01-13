package com.bestride.data.helper;


public class Goods {

	private String id;
	private String goodsname = "矿泉水";
	private String posid;
	private String goodsclassdesc;
	private String goodssubclassdesc;
	private double smallprice;
	private double medprice;
	private double larprice;
	private int discount;
	private String auxunit;
	private String picpath;
	private int minorder;
	private int service;
	private int servicemode;
	private int servicerate;
	private int discmode;
	private int discrate;
	private int limitedfree;
	private int specprice;
	
	private transient boolean isAdd = false;
	private transient int number;
	
	public Goods() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	public String getGoodsclassdesc() {
		return goodsclassdesc;
	}

	public void setGoodsclassdesc(String goodsclassdesc) {
		this.goodsclassdesc = goodsclassdesc;
	}

	public String getGoodssubclassdesc() {
		return goodssubclassdesc;
	}

	public void setGoodssubclassdesc(String goodssubclassdesc) {
		this.goodssubclassdesc = goodssubclassdesc;
	}

	public double getSmallprice() {
		return smallprice;
	}

	public void setSmallprice(double smallprice) {
		this.smallprice = smallprice;
	}

	public double getMedprice() {
		return medprice;
	}

	public void setMedprice(double medprice) {
		this.medprice = medprice;
	}

	public double getLarprice() {
		return larprice;
	}

	public void setLarprice(double larprice) {
		this.larprice = larprice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getAuxunit() {
		return auxunit;
	}

	public void setAuxunit(String auxunit) {
		this.auxunit = auxunit;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public int getMinorder() {
		return minorder;
	}

	public void setMinorder(int minorder) {
		this.minorder = minorder;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getServicemode() {
		return servicemode;
	}

	public void setServicemode(int servicemode) {
		this.servicemode = servicemode;
	}

	public int getServicerate() {
		return servicerate;
	}

	public void setServicerate(int servicerate) {
		this.servicerate = servicerate;
	}

	public int getDiscmode() {
		return discmode;
	}

	public void setDiscmode(int discmode) {
		this.discmode = discmode;
	}

	public int getDiscrate() {
		return discrate;
	}

	public void setDiscrate(int discrate) {
		this.discrate = discrate;
	}

	public int getLimitedfree() {
		return limitedfree;
	}

	public void setLimitedfree(int limitedfree) {
		this.limitedfree = limitedfree;
	}

	public int getSpecprice() {
		return specprice;
	}

	public void setSpecprice(int specprice) {
		this.specprice = specprice;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
