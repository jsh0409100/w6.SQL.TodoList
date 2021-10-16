package com.todo;

import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todoList.txt");
		boolean isList = false;
		boolean quit = false;
		
		Menu.displaymenu();
		do {
			Menu.promt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "com":
				String num = sc.nextLine();
				System.out.println("\n");
				TodoUtil.completeItem(l, num);
				break;
			
			case "ls_com":
				System.out.println(">>> �Ϸ�� �׸�");
				TodoUtil.listCom(l, "complete", 1);
				break;
				
			case "ls_ncom":
				System.out.println(">>> �ؾ� �ϴ� �׸�");
				TodoUtil.listCom(l, "complete", 0);
				break;
			
			case "ls_imprt":
				System.out.println(">>> �߿䵵 ������ ����");
				TodoUtil.listAll(l, "importance", 1);
				break;
				
			case "ls_inhurry":
				System.out.println(">>> ���� ������ ���� ����");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_days":
				int due = sc.nextInt();
				System.out.printf(">>> ���÷κ��� " + due + "�� ���� �����ؾ� �ϴ� ���\n");
				TodoUtil.todayDuedate(l, due);
				break;
				
			case "ls_name_a":
				System.out.println(">>> �̸� ������������ ������ ����");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_d":
				System.out.println(">>> �̸� ������������ ����");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date_a":
				System.out.println(">>> ��¥ ������������ ����");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_d":
				System.out.println(">>> ��¥ ������������ ����");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_cate":
				System.out.println(">>> ��ϵ� ī�װ�\n");
				TodoUtil.listCateAll(l);
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				System.out.printf(">>> " + keyword + "�� ����� ������ �˻��� ���\n");
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				System.out.printf(">>> " + cate + "�� ī�װ��� �˻��� ���\n");
				TodoUtil.findCateList(l, cate);
				break;
				
			case "find_due":
				String due_date = sc.nextLine().trim();
				System.out.printf(">>> " + due_date + "�� ������ �˻��� ���\n");
				TodoUtil.findDue(l, due_date);
				break;
				
			case "exit":
				quit = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			
			
			default:
				System.out.println("[Error] ��Ȯ�� ��ɾ �Է��ϼ���. (��ɾ ������ help�� �Է��ϼ���.)");
				break;
			}
		} while (!quit);
	}
}
