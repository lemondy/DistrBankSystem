package com.edu.bit.cs.lemondy.business;

import java.awt.datatransfer.Transferable;
import java.io.Console;
import java.sql.Savepoint;
import java.util.Scanner;

import com.edu.bit.cs.lemondy.entity.LoginStatus;

public class ATM {
	
	private static final String WELCOME = "*** ��ӭʹ�ñ� ATM ϵͳ ***";  
	private static final String USAGE = "********************Welcome********************" +
								  "**** 1.��ѯ                                                          2.ȡ��                         ****" +
								  "**** 3.ת��                                                          4.���                         ****" +
								  "**** 5.�˳�                                                                                                 ****" +
								  "**********************************************";
	private static Scanner scanner = new Scanner(System.in);
	
	public static void mainUI(String card){
		System.out.println(USAGE);
		String step = scanner.nextLine();
		while(!"5".equals(step)){
			switch(step){
			case "1":
				ZooKeeper.queryMoney();
				break;
			case "2":
				ZooKeeper.withDraw();
				break;
			case "3":
				ZooKeeper.transferMoney();
				break;
			case "4":
				ZooKeeper.save();
				break;
				default:
					break;
			}
			System.out.println(USAGE);
			step = scanner.nextLine();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(WELCOME);
		String card = "";
		String paswd = "";
		
		
		System.out.println("�����뿨�ţ�");
		card = scanner.nextLine();
		System.out.println("���������룺");
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
			System.out.println("�û��������");
		}else if(ZooKeeper.login(card, paswd) == LoginStatus.USERNOTEXISTS){
			System.out.println("�û������ڣ�����ע��");
		}
		
	}

}
