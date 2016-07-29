package com.edu.bit.cs.lemondy.entity;

/**
 * 
 * @author lemon
 * Builder pattern
 */
public class Client {
	private final String name;
	private final String sex;
	private final String IDCard;
	private int age;
	private String phoneNumber;
	private String address;
	
	
	public static class Builder{
		private final String name;
		private final String IDCard;
		
		private String sex = "male";
		private int age = 0;
		private String phoneNumber = "";
		private String address = "";
		
		public Builder(String name, String IDCard){
			this.name = name;
			this.IDCard = IDCard;
		}
		
		public Builder sex(String val){
			sex = val; return this;
		}
		
		public Builder age(int val){
			age = val; return this;
		}
		
		public Builder phoneNumber(String val){
			phoneNumber = val; return this;
		}
		
		public Builder address(String val){
			address = val; return this;
		}
		
		public Client build(){
			return new Client(this);
		}
		
	}
	
	private Client(Builder builder){
		name = builder.name;
		sex = builder.sex;
		IDCard = builder.IDCard;
		age = builder.age;
		phoneNumber = builder.phoneNumber;
		address = builder.address;
	}
	
	//generate a new object
	//Client  client = new Client.Builder("Tom", "12345...").sex("female").age(30)
	//					.phoneNumber("13234567894").address("xsdf").build();
}
