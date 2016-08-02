package com.edu.bit.cs.lemondy.business;

import java.util.Scanner;

import com.edu.bit.cs.lemondy.entity.LoginStatus;
import com.edu.bit.cs.lemondy.entity.OPERATIONS;

public class ATM {
	
	private static final String WELCOME = "*** 欢迎使用本 ATM 系统 ***";  
	private static final String USAGE = "********************Welcome********************" +
								  "**** 1.查询                                                          2.取款                         ****" +
								  "**** 3.转账                                                          4.存款                         ****" +
								  "**** 5.退出                                                                                                 ****" +
								  "**********************************************";
	private static Scanner scanner = new Scanner(System.in);
	
	public static void mainUI(String card){
		System.out.println(USAGE);
		String step = scanner.nextLine();
		OPERATIONS op = OPERATIONS.DEFAULT;
		while(!"5".equals(step)){
			switch(step){
			case "1":
				ZooKeeper.queryMoney(card);
				op = OPERATIONS.QUERY;
				break;
			case "2":
				System.out.println("请输入取款金额：");
				int money = scanner.nextInt(); 
				ZooKeeper.withDraw(card, money);
				op = OPERATIONS.WITHDRAW;
				break;
			case "3":
				System.out.println("请输入转入账号：");
				String other_card = scanner.next();
				System.out.println("请输入转账金额：");
				int trans_money = scanner.nextInt();
				ZooKeeper.transferMoney(card, other_card, trans_money);
				op= OPERATIONS.TRANSFER;
				break;
			case "4":
				System.out.println("请输入存款金额：");
				int save_money = scanner.nextInt();
				ZooKeeper.save(card, save_money);
				op = OPERATIONS.SAVE;
				break;
				default:
					break;
			}
			ZooKeeper.writeLog(op);
			System.out.println(USAGE);
			step = scanner.nextLine();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(WELCOME);
		String card = "";
		String paswd = "";
		
		
		System.out.println("请输入卡号：");
		card = scanner.nextLine();
		System.out.println("请输入密码：");
		paswd = scanner.nextLine();
		
		LoginStatus status =ZooKeeper.login(card, paswd);
		
		switch(status){
		case SUCCESS:
			mainUI(card);
			break;
		case PASWDERR:
			break;
		case USERNOTEXISTS:
			break;
		default:
			
				
			
		
		}
		if(ZooKeeper.login(card, paswd) == LoginStatus.SUCCESS){
			System.out.println(USAGE);
		}else if(ZooKeeper.login(card, paswd) == LoginStatus.PASWDERR){
			System.out.println("用户密码错误");
		}else if(ZooKeeper.login(card, paswd) == LoginStatus.USERNOTEXISTS){
			System.out.println("用户不存在，请先注册");
		}
		
	}

}
