package com.edu.bit.cs.lemondy.business;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.edu.bit.cs.lemondy.db.DBManager;
import com.edu.bit.cs.lemondy.entity.LoginStatus;
import com.edu.bit.cs.lemondy.entity.OPERATIONS;

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
		String sql = "select * from user where card='"+card+"'";
		ResultSet rs=DBManager.execQuery(bankName, sql);
		float yuE = 0.0f;
		try{
			while(rs.next()){
				yuE = rs.getFloat("money");
			}
			
		}catch(SQLException e){
			System.err.println("余额查询失败"+e.toString());
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
					System.err.println("数据库关闭失败"+e2.toString());
				}
				
			}
		}
		return yuE;
	}
	public static boolean withDraw(String card, int money){
		String bankName = getUserBank(card);
		float yuE = queryMoney(card);
		String sql = "update user set money="+(yuE-money)+
				" where card='"+card+"'";
		
	
		if(money > yuE){
			System.out.println("余额不足。");
			return false;
		}
		DBManager.execUpdate(bankName, sql);
		return true;
		
	}
	public static boolean transferMoney(String card, String other_card, int money){
		String bankName = getUserBank(card);
		String bankNameOther = getUserBank(other_card);
		float yuE = queryMoney(card);
		float yuEOther = queryMoney(other_card); 
		if(money>yuE){
			System.err.println("余额不足，无法转账.");
			return false;
		}
		String sql = "update user set money = "+(yuE-money)+" where card='"+card+"';";
		String sqlOther = "update user set money = "+(yuEOther+money)+" where card='"+other_card+ "';";
		DBManager.execUpdate(bankName, sql);
		DBManager.execUpdate(bankNameOther, sqlOther);
		return true;
			
	}
	
	public static boolean save(String card, int money){
		String bankName = getUserBank(card);
		float yuE = queryMoney(card);
		String sql = "update user set money='"+(yuE+money)+" where card='"+card+"';";
		
		DBManager.execUpdate(bankName, sql);
		return true;
	}
	
	/**
	 * log: 格式
	 */
	public static void writeLog(OPERATIONS op){
		
	}
}
