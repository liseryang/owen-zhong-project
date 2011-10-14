package com.oz.springmvc.test.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_BOOKING")
public class Hello implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String bookingNo;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_BOOKING", unique = true, nullable = false, columnDefinition = "int(11) unsigned", length = 11)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "VR_BKG_BOOKING_NO", length = 30)
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
	public String toString(){
		return this.id+":"+this.bookingNo+"<br />";
	}
}
