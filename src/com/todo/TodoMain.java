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
				System.out.println(">>> 완료된 항목");
				TodoUtil.listCom(l, "complete", 1);
				break;
				
			case "ls_ncom":
				System.out.println(">>> 해야 하는 항목");
				TodoUtil.listCom(l, "complete", 0);
				break;
			
			case "ls_imprt":
				System.out.println(">>> 중요도 순으로 정렬");
				TodoUtil.listAll(l, "importance", 1);
				break;
				
			case "ls_inhurry":
				System.out.println(">>> 마감 순서가 빠른 정렬");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_days":
				int due = sc.nextInt();
				System.out.printf(">>> 오늘로부터 " + due + "일 내에 마감해야 하는 목록\n");
				TodoUtil.todayDuedate(l, due);
				break;
				
			case "ls_name_a":
				System.out.println(">>> 이름 오름차순으로 순으로 정렬");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_d":
				System.out.println(">>> 이름 내림차순으로 정렬");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date_a":
				System.out.println(">>> 날짜 오름차순으로 정렬");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_d":
				System.out.println(">>> 날짜 내림차순으로 정렬");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_cate":
				System.out.println(">>> 등록된 카테고리\n");
				TodoUtil.listCateAll(l);
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				System.out.printf(">>> " + keyword + "로 제목과 설명을 검색한 결과\n");
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				System.out.printf(">>> " + cate + "로 카테고리를 검색한 결과\n");
				TodoUtil.findCateList(l, cate);
				break;
				
			case "find_due":
				String due_date = sc.nextLine().trim();
				System.out.printf(">>> " + due_date + "로 마감일 검색한 결과\n");
				TodoUtil.findDue(l, due_date);
				break;
				
			case "exit":
				quit = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			
			
			default:
				System.out.println("[Error] 정확한 명령어를 입력하세요. (명령어를 보려면 help를 입력하세요.)");
				break;
			}
		} while (!quit);
	}
}
