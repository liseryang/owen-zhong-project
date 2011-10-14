package com.oz.springmvc.framework;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class JacksonObjectMapper {
	private static ObjectMapper instance = null;
	static{
		instance = new ObjectMapper();
		instance.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		instance.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		instance.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		instance.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		instance.configure(Feature.ALLOW_SINGLE_QUOTES, true);
	}
	public static ObjectMapper getInstance() {
		return instance;
	}
	public static void main(String[] args) {
		TestBean testBean = new TestBean();
		ObjectMapper objectMapper = JacksonObjectMapper.getInstance();
		try {
			String writeValueAsString = objectMapper.writeValueAsString(testBean);
			System.out.println(writeValueAsString);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class TestBean {
	private Integer i;
	private Long id;
	private String s;
	private Float f;
	private Date d;
	private BigDecimal bd;
	public TestBean(){}
	public Integer getI() {
		return i;
	}
	public void setI(Integer i) {
		this.i = i;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public Float getF() {
		return f;
	}
	public void setF(Float f) {
		this.f = f;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public BigDecimal getBd() {
		return bd;
	}
	public void setBd(BigDecimal bd) {
		this.bd = bd;
	}
	public void setId(Long l) {
		this.id = l;
	}
	public Long getId() {
		return id;
	}
	
}