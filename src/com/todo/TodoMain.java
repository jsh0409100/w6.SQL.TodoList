package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		l.importData("todoList.txt");
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

			case "ls_name_a":
				System.out.println(">>> �̸� ������������ ������ ����");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_d":
				System.out.println(">>> �̸� ������������ ����");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println(">>> ��¥ ������ ����");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println(">>> ��¥ ������ ����");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
			
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "exit":
				quit = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
			
			default:
				System.out.println("[Error] ��Ȯ�� ��ɾ �Է��ϼ���. (��ɾ ������ help�� �Է��ϼ���.)");
				break;
			}
		} while (!quit);
	}
}
