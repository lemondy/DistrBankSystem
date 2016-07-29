package com.edu.bit.cs.lemondy.business;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.bit.cs.lemondy.db.DBManager;
import com.edu.bit.cs.lemondy.entity.LoginStatus;

public class ZooKeeper {

	private static String getUserBank(String card){
		ResultSet rs = DBManager.execQuery("bank", "select bank_name from bank where card='"+card+"';");
		String bankName = "";
		try {
			while(rs.next()){
				bankName = rs.getString("bank_name");
			}
			rs.close(); 
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("数据库查询异常");
		}		
		return bankName;
		
	}
	
	public static LoginStatus login(String card, String paswd){
		String bankName = getUserBank(card);
		ResultSet rs = DBManager.execQuery(bankName, "select paswd from client where card='"+card+"';");
		String pwd = "";
		try {
			while(rs.next()){
				pwd = rs.getString("paswd");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if("".equals(pwd)){
			return LoginStatus.USERNOTEXISTS;
		}else if(pwd.equals(paswd)){
			return LoginStatus.SUCCESS;
		}else{
			return LoginStatus.PASWDERR;
		}
	}
	
	public static float queryMoney(String card){
		String bankName = getUserBank(card);
		
	}
	public static boolean withDraw(String card, int money){
		
	}
	public static boolean transferMoney(String card, String other_card, int money){
		
	}
	
	public static boolean save(String card, int money){
		
	}
	
	/**
	 * 
	 */
	public static void writeLog(){
		
	}
}
