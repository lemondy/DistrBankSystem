package com.edu.bit.cs.lemondy.entity;

public interface Bank {
	public boolean verify();
	public float query();
	public boolean withdraw(int money);
	public boolean save(int money);
	public boolean tranfer(String IDCard, float money);
}
