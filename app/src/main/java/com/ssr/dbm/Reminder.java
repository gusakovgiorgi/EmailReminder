package com.ssr.dbm;

import java.io.Serializable;

public final class Reminder implements Serializable {
	private static final long serialVersionUID = -8755228720844914784L;
	private int id;
	private String type;

	private String title;
	private String detail;

	private String phoneNum;
	private String smstext;

	private String subject;
	private String emailtext;
	private String emailaddress;

	private String birthdayof;
	private String AI;

	private String date;
	private String time;

	private String notification;
	private String batteryperc;
	private String latitude;
	private String longitude;
	private int distance;
	private int distanceCovered;

	private String status;

	public Reminder() {
		id = 0;
		type = "";

		title = "";
		detail = "";

		phoneNum = "";
		smstext = "";

		birthdayof = "";
		AI = "";

		date = "";
		time = "";

		notification = "";
		batteryperc = "";
		latitude = "";
		longitude = "";
		distance = 0;
		distanceCovered = 0;

		status = "";
	}

	public Reminder(int id, String type, String title, String detail,
			String phoneNum, String smstext, String birthdayof, String aI,
			String date, String time, String notification, String batteryperc,
			String latitude, String longitude, int distance,String emailtext,String emailaddress,String subject) {

		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.detail = detail;
		this.phoneNum = phoneNum;
		this.smstext = smstext;
		this.birthdayof = birthdayof;
		AI = aI;
		this.date = date;
		this.time = time;
		this.notification = notification;
		this.batteryperc = batteryperc;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
        this.emailtext=emailtext;
        this.emailaddress=emailaddress;
		this.subject=subject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
    public String getEmailaddress(){
        return emailaddress;
    }

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getSmstext() {
		return smstext;
	}

	public void setSmstext(String smstext) {
		this.smstext = smstext;
	}
	public void setEmailtext(String emailtext) {
		this.emailtext = emailtext;
	}
    public String getEmailtext(){
        return emailtext;
    }

	public String getBirthdayof() {
		return birthdayof;
	}

	public void setBirthdayof(String birthdayof) {
		this.birthdayof = birthdayof;
	}

	public String getAI() {
		return AI;
	}

	public void setAI(String aI) {
		AI = aI;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getBatteryperc() {
		return batteryperc;
	}

	public void setBatteryperc(String batteryperc) {
		this.batteryperc = batteryperc;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistanceCovered() {
		return distanceCovered;
	}

	public void setDistanceCovered(int distanceCovered) {
		this.distanceCovered = distanceCovered;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
